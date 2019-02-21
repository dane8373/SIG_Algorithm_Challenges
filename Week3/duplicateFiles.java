//based off https://leetcode.com/problems/find-duplicate-file-in-system/
//a lot of string manipulation in this one
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        //one hashmap to store filename, file contents
        HashMap<String,String> files = new HashMap<String,String>();
        //one hashmap to store duplicates and a tag for themp
        //we will use the first file name we see associated with these contents as the label
        HashMap<String, List<String>> dups = new HashMap<String, List<String>>();
        List<List<String>> ret = new ArrayList<List<String>>();
        //loop through all the paths
        for (int i=0; i<paths.length; i++) {
            List<String> pathFiles = new ArrayList<String>();
            String rootPath = "";
            int j = 0;
            //encode all characters prior to a space as the patch
            while (j < paths[i].length() && paths[i].charAt(j) != ' ') {
                rootPath+=paths[i].charAt(j++);
            }
            j++;
            //keep going to get all files in the string
            while (j < paths[i].length()) {
                String tempFile = "";
                //keep reading the filename until (
                while (j < paths[i].length() && paths[i].charAt(j) != '(') {
                    tempFile+=paths[i].charAt(j++);
                }
                j++;
                //now read the contents
                String contents = "";
                while (j < paths[i].length() && paths[i].charAt(j) != ')') {
                    contents+=paths[i].charAt(j++);
                }
                j++;
                //if there is a space skip it so the next loop iteration is in line with a filename
                if (j < paths[i].length() && paths[i].charAt(j) == ' ') {
                    j++;
                }
                String fileName = rootPath + "/" + tempFile;
                //if we already have a file with theses same contents
                //we are adding this file to the dup list
                if (files.containsKey(contents)) {
                    List<String> temp;
                    //get the list for these contents
                    if (dups.containsKey(files.get(contents))) {
                        temp = dups.get(files.get(contents));
                    }
                    //if there isn't a list associated with these contents, make one
                    else {
                        temp = new ArrayList<String>();
                        //the label is the first file with these contents, so add it to the list
                        temp.add(files.get(contents));
                    }
                    //add the file to the list
                    temp.add(fileName);
                    dups.put(files.get(contents),temp);
                }
                else {
                    //add this file content pair to our map
                    files.put(contents, fileName);
                }
            }
        }
        //add all of our duplicates into the double list we need to return
        for (String key: dups.keySet()) {
            ArrayList<String> tempDups = new ArrayList<String>();
            for (String file: dups.get(key)) {
                tempDups.add(file);
            }
            ret.add(tempDups);
        }
        return ret;
    }
}