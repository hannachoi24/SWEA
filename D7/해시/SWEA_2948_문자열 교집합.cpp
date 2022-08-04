#define _CRT_SECURE_NO_DEPRECATE
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <unordered_map>
using namespace std;

int main(void)
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T, n, m;

    cin >> T;

    for (int tc = 1; tc <= T; tc++)
    {
        cin >> n >> m;
        string s;
        unordered_map<string, string> hash;
        int cnt = 0;

        for (int i = 0; i < n; i++)
        {
            cin >> s;
            hash.insert({s, s}); // 해시에 첫 집합의 문자열들을 저장
        }
        for (int i = 0; i < m; i++)
        {
            cin >> s;
            if (hash.find(s) != hash.end()) // 두 번째 문자열 집합을 입력받을 때 검색해서 해시에 들어있는 문자열의 개수를 세준다.
            {
                cnt++;
            }
        }

        cout << "#" << tc << " " << cnt << '\n';
    }

    return 0;
}