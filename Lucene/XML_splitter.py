import xml.etree.cElementTree as ET
import os.path
import json
import xml.sax


if __name__ == '__main__':

    count = 0
    count2 = 0
    testdict = dict()
    for event, elem in ET.iterparse("/home/senne/School/Master_AI_1/Posts.xml", events=("start", "end")):
        if event == "end":
            if count2 % 10000 == 0:
                print(count2)
            if elem.attrib['PostTypeId'] == '1' and count < 1000:
                if not os.path.isfile('SplittedXML/PageID' + elem.attrib['Id']):
                    f = open("SplittedXML/PageID" + elem.attrib['Id'], "w+")
                    f.write(json.dumps(elem.attrib))
                    f.write('\n')
                    f.close()
                count += 1


            elif elem.attrib['PostTypeId'] == '2':
                if os.path.isfile('SplittedXML/PageID' + elem.attrib['ParentId']):
                    f = open('SplittedXML/PageID' + elem.attrib['ParentId'], "a+")
                    f.write(json.dumps(elem.attrib))
                    f.write('\n')
            else:
                pass

            count2 += 1

    '''
    f = open('SplittedXML/PageID4')
    for line in f:

        obj = json.loads(line)
        print("t")
    '''