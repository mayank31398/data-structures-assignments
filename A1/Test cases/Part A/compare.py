f = open("result.txt", "w")

for r in range(7):
	answer_list = []
	output_list = []
	for line in open("answer{}.txt".format(r), "r"):
		answer_list.append([int(i) for i in line.split()])
	try:
		for line in open("output{}.dat".format(r), "r"):
			output_list.append([int(i) for i in line.split()])
		if answer_list == output_list:
			f.write("test{} : Pass\n".format(r))
		else:
			f.write("test{} : Fail\n".format(r))
	except ValueError:
		f.write("test{} : Fail - File not found\n".format(r))