# patiently

Patient assertions for polling or retrying tasks.

For further information, I wrote [this blog](https://github.com/maankoe/patiently).

# Usage

```
Task slowTask = Task();
int retryIntervalMs = 100;
int timeLimitMs = 1000;
Executors.newSingleThreadExecutor().execute(slowTask);
Patently.retry(() -> assertThat(slowTask.isComplete()).isTrue())
	.every(retryIntervalMs)
	.until(timeLimitMs);
```

In general, you can do the following for a `task` that is either a `Runnable`, `Callable<T>`, or `Supplier<Boolean>`:

```
Patiently.retry(task)
	.every(everyMs)
	.until(untilMs);
```

where `long everyMs` specifies the interval between retries (how often the task is executed) and `long untilMs` specifies the when the task will stop being retried. After `untilMs`, the task will be executed one final time and the result returned (or Exception thrown).

Alternatively, there is an all-in-one:

```
Patiently.retry(task, everyMs, untilMs);
```

Additionally, `retry` can be imported statically.

