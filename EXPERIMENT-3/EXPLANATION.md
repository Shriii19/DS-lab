# EXPERIMENT 3 — OpenMP Distributed Sum (Parallel Computing in C++)

## What is this?
**OpenMP** is a tool for making a program run **faster by using multiple CPU cores at the same time** (parallel processing). Instead of one thread adding up all numbers one by one, multiple threads each add a portion and combine the results.

---

## File: `distributed_sum.cpp`

### Step-by-Step Explanation

```cpp
#pragma omp parallel
{
    int threadSum = 0;         // Each thread has its OWN local sum
    #pragma omp for
    for (int i = 0; i < N; i++) {
        threadSum += arr[i];   // Each thread processes DIFFERENT elements
    }
    #pragma omp critical
    {
        totalSum += threadSum; // One at a time: add local sum to global sum
    }
}
```

### Keyword Reference

| Keyword | Meaning |
|---|---|
| `#pragma omp parallel` | Start a team of threads — they all run this block simultaneously |
| `#pragma omp for` | Split the loop iterations among the threads |
| `#pragma omp critical` | Only ONE thread can run this block at a time (prevents data corruption) |

---

## Example
If array = `[1, 2, 3, 4, 5, 6]` with 2 threads:
- Thread 0 processes `[1, 2, 3]` → partial sum = 6
- Thread 1 processes `[4, 5, 6]` → partial sum = 15
- Final sum = 6 + 15 = **21**

---

## Why `#pragma omp critical`?
Without it, both threads could try to update `totalSum` at the same moment, causing a **race condition** (wrong result). The `critical` section ensures only one thread updates `totalSum` at a time.
