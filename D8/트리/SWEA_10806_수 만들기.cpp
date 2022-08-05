#include <iostream>
#include <queue>

using namespace std;

int N, K, ans;
int Nums[11];

void Input()
{
    cin >> N;

    for (int i = 0; i < N; i++)
    {
        cin >> Nums[i];
    }

    cin >> K;
}

void GetAns()
{
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push(make_pair(0, K));

    while (pq.top().second)
    {
        pair<int, int> cur = pq.top();
        pq.pop();

        pq.push(make_pair(cur.first + cur.second, 0));

        for (int i = 0; i < N; i++)
        {
            pq.push(make_pair(cur.first + cur.second % Nums[i], cur.second / Nums[i]));
        }
    }
    ans = pq.top().first;
}

int main()
{
    int T;
    cin >> T;

    for (int tc = 1; tc <= T; tc++)
    {
        Input();
        GetAns();
        cout << "#" << tc << " " << ans << '\n';
    }
    return 0;
}

/*
K = a^3 * b^2 * c^1 + a^2 * b^1 + a^1 + 1과 같은 형태로 나오게된다. K를 다음과 같이 계속 나누면서 풀어쓸 수 있다는 것이다.

K = a^3 * b^2 * c^1 + a^2 * b^1 + a^1 + 1
K_1 (+1 제거, 즉 더하기 요소 하나 제거) = a^3 * b^2 * c^1 + a^2 * b^1 + a^1
K_1 나누기 a^1                                = a^2 * b^2 * c^1 + a^1 * b^1 + 1
K_2 (+1 제거)                                   = a^2 * b^2 * c^1 + a^1 * b^1
K_2 나누기 a^1 + b^1                       = a^1 * b^1 * c^1 + 1 
K_3 (+1 제거)                                   = a^1 * b^1 * c^1
K_3 나누기 a^1 * b^1 * c^1                = 1
K_4 (+1 제거)                                   = 0 = K로 가기위한 X의 시작값

=> 시작값 K에서 a,b,c...로 만든 값을 빼고, 나누는 과정을 반복해서 0이 되게 만들고 빼는 횟수가 최소인것이 정답이다. 

코드로 짜기위해 순서대로 생각해보면
1. 현재 시도회수 cnt와 0으로 가기위해 남은 값 left로 만들어진 obj 구조체를 만든다.
2. 우선순위 큐인 pq에 obj(cnt = 0, left = K)를 넣는다
3. pq에서 cnt가 최소인 min_obj을 꺼낸다.
4. pq에 새로운 obj(cnt = min_obj.cnt + min_obj.left, left = 0)을 pq에 넣는다. (더이상 나눌 수 있는 a,b,c... 가 없어서 현재 수에서 계속 빼야하는 경우)
5. N개의 a,b,c...를 돌며 새로운 obj(cnt = min_obj.cnt + min_obj.left % a,b,c..., left = min_obj.left / a,b,c...)를 pq에 넣는다
6. 3번부터 다시 무한 반복, 다만 min_obj.left가 0이면 멈춘다
7. 이때 min_obj.cnt가 정답이다.
*/