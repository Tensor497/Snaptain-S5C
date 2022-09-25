package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.plugins.RxJavaPlugins;

public final class CompletableFromAction extends Completable {
  final Action run;
  
  public CompletableFromAction(Action paramAction) {
    this.run = paramAction;
  }
  
  protected void subscribeActual(CompletableObserver paramCompletableObserver) {
    Disposable disposable = Disposables.empty();
    paramCompletableObserver.onSubscribe(disposable);
    try {
      return;
    } finally {
      Exception exception = null;
      Exceptions.throwIfFatal(exception);
      if (!disposable.isDisposed()) {
        paramCompletableObserver.onError(exception);
      } else {
        RxJavaPlugins.onError(exception);
      } 
    } 
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/io/reactivex/internal/operators/completable/CompletableFromAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */