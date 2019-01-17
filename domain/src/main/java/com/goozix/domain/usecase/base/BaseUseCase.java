package com.goozix.domain.usecase.base;

import com.goozix.domain.executors.ExecutionThread;
import com.goozix.domain.executors.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase {
    protected Scheduler executionThread;
    protected Scheduler postExecutionThread;

    public BaseUseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    public BaseUseCase(ExecutionThread threadExecutor, PostExecutionThread postExecutionThread){
        this.executionThread = Schedulers.from(threadExecutor);
        this.postExecutionThread = postExecutionThread.getScheduler();
    }

    public BaseUseCase(PostExecutionThread postExecutionThread){
        this.executionThread = Schedulers.io();
        this.postExecutionThread = postExecutionThread.getScheduler();
    }
}
