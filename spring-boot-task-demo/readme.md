# Spring Task and Scheduler
## Spring Task and Scheduler关键类
 - TaskDecorator
 - TaskExecutor  SyncTaskExecutor、SimpleAsyncTaskExecutor、ConcurrentTaskExecutor、ThreadPoolTaskExecutor、WorkManagerTaskExecutor、DefaultManagedTaskExecutor
 - TaskScheduler 
 - Trigger 
 - TriggerContext 
 - ExecutorConfigurationSupport
 
## Spring @Scheduled 注解实现
ScheduledAnnotationBeanPostProcessor
ThreadPoolTaskScheduler
ScheduledTaskRegistrar
ScheduledThreadPoolExecutor
ScheduledMethodRunnable