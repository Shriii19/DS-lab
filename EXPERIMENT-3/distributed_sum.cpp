#include <iostream>
#include <vector>
#include <omp.h>
using namespace std;

int main() {
    int N;
    cout << "Enter number of elements (N): ";
    cin >> N;
    vector<int> arr(N);
    cout << "Enter elements:\n";
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    int totalSum = 0;

#pragma omp parallel
    {
        int threadSum = 0;
#pragma omp for
        for (int i = 0; i < N; i++) {
            threadSum += arr[i];
        }
#pragma omp critical
        {
            cout << "Thread " << omp_get_thread_num() << " partial sum = " << threadSum << endl;
            totalSum += threadSum;
        }
    }

    cout << "\nFinal Sum = " << totalSum << endl;
    return 0;
}