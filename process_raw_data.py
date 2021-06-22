import sys
import re
import pymongo
import time
import hashlib

# if enable debug level on log
DEBUG_LOG = False

# mongodb url
MONGO_CONNECT_URL = 'mongodb://localhost:27017/'

# mongodb db name
MONGO_DB_NAME = 'scu_sport_exam_lib'

# mongodb question collection name
MONGO_MAIN_COLLECTION = 'question_record'

# mongodb record structure
MONGO_MAIN_RECORD_TEMPLATE = {
	'_id': None,
	'raw_text': None,
	'raw_text_md5': None,
	'gmt_create': None,
	'gmt_modified': None,
	'question_type': None,
	'question_body': None,
	'question_option': None,
	'question_answer': None
}

# config for mongodb
mongo_client = pymongo.MongoClient(MONGO_CONNECT_URL)
db = mongo_client[MONGO_DB_NAME]
question_collection = db[MONGO_MAIN_COLLECTION]
_id = 0


def log(text, level='INFO'):
	"""
	log util tool
	"""
	if level == 'DEBUG' and not DEBUG_LOG:
		return
	print('[{}] {}'.format(level, str(text)))
	pass


def current_timestamp_milli():
	return int(round(time.time() * 1000))
	pass


def md5(text):
	m = hashlib.md5()
	m.update(text.encode('utf-8'))
	return str(m.hexdigest())
	pass


def main(path):
	"""
	script entry
	"""
	with open(path, 'r') as f:
		text = f.read()

	block_list = text.split('\n')

	# process start
	sub_question = []
	for block in block_list:
		if len(block) != 0:
			sub_question.append(block)
		elif len(sub_question) != 0:
			# sub question end
			# avoid empty question

			# test seq question
			find_res = re.findall('你的答案', ''.join(sub_question))
			if len(find_res) > 1:
				# process seq question
				final_sub_question_list = []
				for sub_line in sub_question:
					if '你的答案' in sub_line:
						final_sub_question_list.append(sub_line)
						final_sub_text = '\n'.join(final_sub_question_list)

						save_in_db(final_sub_text, question_collection)
						log('catch seq question, line list length={}, text={}'.format(len(final_sub_question_list), final_sub_text), 'DEBUG')
						final_sub_question_list = []
						pass
					else:
						final_sub_question_list.append(sub_line)
						pass
					pass
				pass
				sub_question = []
			else:
				# normal save in list
				final_text = '\n'.join(sub_question)

				save_in_db(final_text, question_collection)
				log('normal question, line list length={}, text={}'.format(len(sub_question), final_text), 'DEBUG')
				sub_question = []
	pass


def save_in_db(question_text, collection):
	global _id
	# confirm type
	que_choose_type = 'A' in question_text and 'B' in question_text and 'C' in question_text

	# process body options answer
	process_text = question_text
	que_body = []
	que_option_list = []
	que_answer = []
	process_text_split_list = process_text.split('\n')
	for split_line in process_text_split_list:
		split_line = split_line.strip()
		if split_line.startswith('A') or split_line.startswith('B') or split_line.startswith('C') or split_line.startswith('D'):
			que_option_list.append(split_line)
		elif split_line.startswith('你的答案'):
			que_answer.append(split_line)
		else:
			que_body.append(split_line)

	_id = _id + 1
	MONGO_MAIN_RECORD_TEMPLATE['_id'] = _id
	MONGO_MAIN_RECORD_TEMPLATE['raw_text'] = question_text
	MONGO_MAIN_RECORD_TEMPLATE['raw_text_md5'] = md5(question_text)
	MONGO_MAIN_RECORD_TEMPLATE['gmt_create'] = current_timestamp_milli()
	MONGO_MAIN_RECORD_TEMPLATE['gmt_modified'] = current_timestamp_milli()
	MONGO_MAIN_RECORD_TEMPLATE['question_type'] = '选择题' if que_choose_type else '判断题'
	MONGO_MAIN_RECORD_TEMPLATE['question_body'] = '\n'.join(que_body)
	MONGO_MAIN_RECORD_TEMPLATE['question_option'] = que_option_list
	MONGO_MAIN_RECORD_TEMPLATE['question_answer'] = '\n'.join(que_answer)
	collection.insert_one(MONGO_MAIN_RECORD_TEMPLATE)
	log('save {} into collection success'.format(MONGO_MAIN_RECORD_TEMPLATE))
	pass


if __name__ == '__main__':
	main(sys.argv[1])
