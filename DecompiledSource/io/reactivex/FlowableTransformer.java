package io.reactivex;

import org.reactivestreams.Publisher;

public interface FlowableTransformer<Upstream, Downstream> {
  Publisher<Downstream> apply(Flowable<Upstream> paramFlowable);
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/io/reactivex/FlowableTransformer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */