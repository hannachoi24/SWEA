#define _CRT_SECURE_NO_DEPRECATE
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int T, k;

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> T;

    string s;

    for (int tc = 1; tc <= T; tc++)
    {
        cin >> k;
        cin >> s;
        vector<string> list;
        string tmp = "";

        for (int i = s.length() - 1; i >= 0; i--)
        {
            tmp = s[i] + tmp;
            list.push_back(tmp);
        }

        sort(list.begin(), list.end());

        cout << "#" << tc << " " << list[k - 1] << '\n';
    }

    return 0;
}