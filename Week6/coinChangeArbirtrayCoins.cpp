// based off https://www.hackerrank.com/challenges/coin-change/problem
//lot of weird types because hackerrank is annoying
int64_t helpGetWays(int64_t n, vector<int64_t> & c) {
    static unordered_map<string, int64_t> solutions;
    //there are 0 ways to make change for <0
    if (n<0) {
        return 0;
    }
    //only one way to make change for 0
    if (n==0) {
        return 1;
    }
    //cant make change without any coins
    if (c.size() == 0) {
         return 0;
    }
    int64_t count=0;
    int64_t size=c.size();
    //vector used to store the coins to be used 
    //in the next sub problem
    vector<int64_t> tempc;
    //key used to identify each of the sub problems
    string hashString= "" + to_string(n) +"-";
    //is off the form "(value)-(Coin1)(Coin2)(Coin3)(Coin4)...(CoinN)"
    //denoting the value and which coins we have
    for (int64_t i=0; i<size; i++) {
        hashString = hashString + to_string(c.at(i));
    }
    //if we already solved this subproblem, return the answer we found
    if (solutions.find(hashString) != solutions.end()) {
            cout << hashString << endl;
            cout << "solution " << solutions[hashString] <<endl;
            return solutions[hashString];
    } 
    //loop through the available coins in smallest to biggest order
    for (int64_t i=0; i<size; i++) {
        tempc.clear();
        //try to take away the coin at index i
        int64_t temp = n - c.at(i);
        //if we get to a coin that is too big, break out of the loop
        if (temp<0) {
            break;
        }
        //only allow the next sub problem to used coins >=coin at index i
        //this is to avoid duplicates (e.g. make change for 5 with (2,3) versus (3,2))
        //would get counted as 2 different ways without this, even though they are both
        //one 3 coin and one 2 coin.
        for (int j=i; j<size; j++) {
            tempc.push_back(c.at(j));
        }
            count+=helpGetWays(temp, tempc);
    }
    //store our answer in the hash table
    solutions[hashString]=count;
    return count;
}

int64_t getWays(int64_t n, vector<int64_t> & c) {
    sort(c.begin(), c.end());
    int64_t count = helpGetWays(n, c);
    return count;
}
