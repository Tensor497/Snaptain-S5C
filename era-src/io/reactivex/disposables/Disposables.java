package io.reactivex.disposables;

import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.Future;
import org.reactivestreams.Subscription;

public final class Disposables {
  private Disposables() {
    throw new IllegalStateException("No instances!");
  }
  
  public static Disposable disposed() {
    return (Disposable)EmptyDisposable.INSTANCE;
  }
  
  public static Disposable empty() {
    return fromRunnable(Functions.EMPTY_RUNNABLE);
  }
  
  public static Disposable fromAction(Action paramAction) {
    ObjectHelper.requireNonNull(paramAction, "run is null");
    return new ActionDisposable(paramAction);
  }
  
  public static Disposable fromFuture(Future<?> paramFuture) {
    ObjectHelper.requireNonNull(paramFuture, "future is null");
    return fromFuture(paramFuture, true);
  }
  
  public static Disposable fromFuture(Future<?> paramFuture, boolean paramBoolean) {
    ObjectHelper.requireNonNull(paramFuture, "future is null");
    return new FutureDisposable(paramFuture, paramBoolean);
  }
  
  public static Disposable fromRunnable(Runnable paramRunnable) {
    ObjectHelper.requireNonNull(paramRunnable, "run is null");
    return new RunnableDisposable(paramRunnable);
  }
  
  public static Disposable fromSubscription(Subscription paramSubscription) {
    ObjectHelper.requireNonNull(paramSubscription, "subscription is null");
    return new SubscriptionDisposable(paramSubscription);
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/io/reactivex/disposables/Disposables.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */