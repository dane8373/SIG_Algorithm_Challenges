//based off https://www.hackerrank.com/challenges/abbr/problem
int abbrev_helper(const string & a, const string & b) {
    int end=a.size();
    int b_size=b.size();
    //if we have an answer for this subproblem, return it
    if (answers[end][b_size] != -1) {
        return answers[end][b_size];
    }
    //if B is empty, then A needs to be only lower case letters
    //which we can delete to make A and B equal
    if (b.size() == 0) {
        for (int i = 0; i < end; i++) {
            //if there are any uppercase letters return
            if (a[i] < 'a') {
                answers[end][b_size] = 0;
                return 0;
            }
        }
        answers[end][b_size] = 1;
        return 1;
    }
    bool ret = false;
    string next_b = b.substr(1);
    for (int i = 0; i < end; i++) {
        //if the current letter is an uppercase letter
        if (a[i] < 'a') {
            //then they better be equal, and the rest of the string
            //must be a valid abbreviation, otherwise this is false
            if (a[i]==b[0]) {
                ret = ret || abbrev_helper(a.substr(i+1),next_b);
            }
            break;
        }
        else {
            //if this is the lower case version of the character in B
            //then make it lower case and see if it makes the strings equal
            if (a[i]-'a'+'A'==b[0]) { 
                ret = ret || abbrev_helper(a.substr(i+1),next_b);
                //if it does break out of the loop and return true
                if (ret) {
                    break;
                }
            }
        }
        //if we run out of letters in A, then we are done and return false
        if (b_size > end - i) {
            break;
        }
        //otherwise we delete the character, we simulate this by just advancing
        //the index i we are using to traverse a
    }
    //add this answer to our list
    answers[end][b_size]=ret;
    return ret;
}

// Complete the abbreviation function below.
string abbreviation(string a, string b) {
    for (int i = 0; i < 1001; i++) {
        for (int j = 0; j < 1001; j++) {
            answers[i][j]=-1;
        }
    }
    return abbrev_helper(a,b) ? "YES" : "NO";
}
