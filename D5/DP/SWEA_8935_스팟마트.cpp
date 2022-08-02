#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
#include <functional>
using namespace std;

//dp[n_i][m_i][skip][get]
//n_i : n봉지의 과자 중 n_i번째를 보고있는 상태(0과 n은 각각 왼쪽 끝, 오른쪽 끝을 의미한다.
//m_i : m봉지의 과자 중 고른 과자의 수
//skip : m봉지 중 스킵한 개수
//get : 위 상태일 때 바로 직전에 과자를 get했는가 (했으면 1)
int dp[3001][101][101][2];

vector<int> vn, vm;
int n, m;
int go(int n_i, int m_i, int skip, int get)
{
    //만약 n봉지 끝까지 갔고 && m봉지도 끝까지 간 경우(고른 과자 + 스킵 과자)
    if (n_i == n && m_i + skip == m)
        return 0;

    int &ret = dp[n_i][m_i][skip][get];
    if (ret != -1)
        return ret;

    ret = 0;

    //고르지 않은 경우
    if (get == 0)
    {
        //만약 n봉지를 다 고르지 않은상태일때
        if (n_i < n)
        {
            ret = max(ret, go(n_i + 1, m_i, skip, 1) + vn[n_i]); //고르거나
            ret = max(ret, go(n_i + 1, m_i, skip, 0));           //고르지 않거나
        }
        //만약 m봉지를 다 고르지 않은상태일때
        if (m_i + skip < m)
        {
            ret = max(ret, go(n_i, m_i + 1, skip, 1) + vm[m_i]); //고르거나
            ret = max(ret, go(n_i, m_i, skip + 1, 0));           //고르지 않거나
        }
    }
    //이미 고른 경우 -> 어디를 스킵할까?
    else
    {
        //만약 n봉지를 다 고르지 않은상태일때
        if (n_i < n)
        {
            ret = max(ret, go(n_i + 1, m_i, skip, 0)); //고르지 않거나
        }
        //만약 m봉지를 다 고르지 않은상태일때
        if (m_i + skip < m)
        {
            ret = max(ret, go(n_i, m_i, skip + 1, 0)); //고르지 않거나
        }
    }
    return ret;
}
int main(int argc, char **argv)
{
    int t;
    cin >> t;
    int num = 1;
    while (t--)
    {
        cin >> n;
        vn.resize(n + 1);
        for (int i = 0; i < n; i++)
            cin >> vn[i];

        cin >> m;
        vm.resize(m + 1);
        for (int i = 0; i < m; i++)
            cin >> vm[i];
        vm[m] = -987654321;
        sort(vm.begin(), vm.end(), greater<int>());

        memset(dp, -1, sizeof(dp));

        cout << "#" << num << " " << go(0, 0, 0, 0) << "\n";
        num++;
    }
    return 0;
}