import xml.etree.ElementTree as ET
import os.path
import json

if __name__ == '__main__':

    tree = ET.parse('example.xml')
    root = tree.getroot()
    for child in root:
        if child.attrib['PostTypeId'] == '1' :
            if not os.path.isfile('SplittedXML/PageID'+child.attrib['Id']):
                f = open("SplittedXML/PageID"+child.attrib['Id'], "w+")
                f.write(json.dumps(child.attrib))
                f.write('\n')
                f.close()

        elif child.attrib['PostTypeId']== '2':
            if os.path.isfile('SplittedXML/PageID'+child.attrib['ParentId']):
                f = open('SplittedXML/PageID'+child.attrib['ParentId'], "a+")
                f.write(json.dumps(child.attrib))
                f.write('\n')
        else:
            print("Help")

    '''
    f = open('SplittedXML/PageID4')
    for line in f:

        obj = json.loads(line)
        print("t")
    '''