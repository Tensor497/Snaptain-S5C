package com.netopsun.ijkvideoview.widget.media;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import tv.danmaku.ijk.media.player_gx.IMediaPlayer;
import tv.danmaku.ijk.media.player_gx.ISurfaceTextureHolder;
import tv.danmaku.ijk.media.player_gx.ISurfaceTextureHost;

public class TextureRenderView extends TextureView implements IRenderView {
  private static final String TAG = "TextureRenderView";
  
  private MeasureHelper mMeasureHelper;
  
  private SurfaceCallback mSurfaceCallback;
  
  public TextureRenderView(Context paramContext) {
    super(paramContext);
    initView(paramContext);
  }
  
  public TextureRenderView(Context paramContext, AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }
  
  public TextureRenderView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext);
  }
  
  public TextureRenderView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    initView(paramContext);
  }
  
  private void initView(Context paramContext) {
    this.mMeasureHelper = new MeasureHelper((View)this);
    this.mSurfaceCallback = new SurfaceCallback(this);
    setSurfaceTextureListener(this.mSurfaceCallback);
  }
  
  public void addRenderCallback(IRenderView.IRenderCallback paramIRenderCallback) {
    this.mSurfaceCallback.addRenderCallback(paramIRenderCallback);
  }
  
  public IRenderView.ISurfaceHolder getSurfaceHolder() {
    return new InternalSurfaceHolder(this, this.mSurfaceCallback.mSurfaceTexture, this.mSurfaceCallback);
  }
  
  public View getView() {
    return (View)this;
  }
  
  protected void onDetachedFromWindow() {
    this.mSurfaceCallback.willDetachFromWindow();
    super.onDetachedFromWindow();
    this.mSurfaceCallback.didDetachFromWindow();
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(TextureRenderView.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(TextureRenderView.class.getName());
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    this.mMeasureHelper.doMeasure(paramInt1, paramInt2);
    setMeasuredDimension(this.mMeasureHelper.getMeasuredWidth(), this.mMeasureHelper.getMeasuredHeight());
  }
  
  public void removeRenderCallback(IRenderView.IRenderCallback paramIRenderCallback) {
    this.mSurfaceCallback.removeRenderCallback(paramIRenderCallback);
  }
  
  public void setAspectRatio(int paramInt) {
    this.mMeasureHelper.setAspectRatio(paramInt);
    requestLayout();
  }
  
  public void setVideoRotation(int paramInt) {
    this.mMeasureHelper.setVideoRotation(paramInt);
    setRotation(paramInt);
  }
  
  public void setVideoSampleAspectRatio(int paramInt1, int paramInt2) {
    if (paramInt1 > 0 && paramInt2 > 0) {
      this.mMeasureHelper.setVideoSampleAspectRatio(paramInt1, paramInt2);
      requestLayout();
    } 
  }
  
  public void setVideoSize(int paramInt1, int paramInt2) {
    if (paramInt1 > 0 && paramInt2 > 0) {
      this.mMeasureHelper.setVideoSize(paramInt1, paramInt2);
      requestLayout();
    } 
  }
  
  public boolean shouldWaitForResize() {
    return false;
  }
  
  private static final class InternalSurfaceHolder implements IRenderView.ISurfaceHolder {
    private SurfaceTexture mSurfaceTexture;
    
    private ISurfaceTextureHost mSurfaceTextureHost;
    
    private TextureRenderView mTextureView;
    
    public InternalSurfaceHolder(TextureRenderView param1TextureRenderView, SurfaceTexture param1SurfaceTexture, ISurfaceTextureHost param1ISurfaceTextureHost) {
      this.mTextureView = param1TextureRenderView;
      this.mSurfaceTexture = param1SurfaceTexture;
      this.mSurfaceTextureHost = param1ISurfaceTextureHost;
    }
    
    public void bindToMediaPlayer(IMediaPlayer param1IMediaPlayer) {
      ISurfaceTextureHolder iSurfaceTextureHolder;
      if (param1IMediaPlayer == null)
        return; 
      if (Build.VERSION.SDK_INT >= 16 && param1IMediaPlayer instanceof ISurfaceTextureHolder) {
        iSurfaceTextureHolder = (ISurfaceTextureHolder)param1IMediaPlayer;
        this.mTextureView.mSurfaceCallback.setOwnSurfaceTexture(false);
        SurfaceTexture surfaceTexture = iSurfaceTextureHolder.getSurfaceTexture();
        if (surfaceTexture != null) {
          this.mTextureView.setSurfaceTexture(surfaceTexture);
        } else {
          iSurfaceTextureHolder.setSurfaceTexture(this.mSurfaceTexture);
          iSurfaceTextureHolder.setSurfaceTextureHost(this.mTextureView.mSurfaceCallback);
        } 
      } else {
        iSurfaceTextureHolder.setSurface(openSurface());
      } 
    }
    
    public IRenderView getRenderView() {
      return this.mTextureView;
    }
    
    public SurfaceHolder getSurfaceHolder() {
      return null;
    }
    
    public SurfaceTexture getSurfaceTexture() {
      return this.mSurfaceTexture;
    }
    
    public Surface openSurface() {
      SurfaceTexture surfaceTexture = this.mSurfaceTexture;
      return (surfaceTexture == null) ? null : new Surface(surfaceTexture);
    }
  }
  
  private static final class SurfaceCallback implements TextureView.SurfaceTextureListener, ISurfaceTextureHost {
    private boolean mDidDetachFromWindow = false;
    
    private int mHeight;
    
    private boolean mIsFormatChanged;
    
    private boolean mOwnSurfaceTexture = true;
    
    private Map<IRenderView.IRenderCallback, Object> mRenderCallbackMap = new ConcurrentHashMap<IRenderView.IRenderCallback, Object>();
    
    private SurfaceTexture mSurfaceTexture;
    
    private WeakReference<TextureRenderView> mWeakRenderView;
    
    private int mWidth;
    
    private boolean mWillDetachFromWindow = false;
    
    public SurfaceCallback(TextureRenderView param1TextureRenderView) {
      this.mWeakRenderView = new WeakReference<TextureRenderView>(param1TextureRenderView);
    }
    
    public void addRenderCallback(IRenderView.IRenderCallback param1IRenderCallback) {
      TextureRenderView.InternalSurfaceHolder internalSurfaceHolder;
      this.mRenderCallbackMap.put(param1IRenderCallback, param1IRenderCallback);
      if (this.mSurfaceTexture != null) {
        internalSurfaceHolder = new TextureRenderView.InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this);
        param1IRenderCallback.onSurfaceCreated(internalSurfaceHolder, this.mWidth, this.mHeight);
      } else {
        internalSurfaceHolder = null;
      } 
      if (this.mIsFormatChanged) {
        TextureRenderView.InternalSurfaceHolder internalSurfaceHolder1 = internalSurfaceHolder;
        if (internalSurfaceHolder == null)
          internalSurfaceHolder1 = new TextureRenderView.InternalSurfaceHolder(this.mWeakRenderView.get(), this.mSurfaceTexture, this); 
        param1IRenderCallback.onSurfaceChanged(internalSurfaceHolder1, 0, this.mWidth, this.mHeight);
      } 
    }
    
    public void didDetachFromWindow() {
      Log.d("TextureRenderView", "didDetachFromWindow()");
      this.mDidDetachFromWindow = true;
    }
    
    public void onSurfaceTextureAvailable(SurfaceTexture param1SurfaceTexture, int param1Int1, int param1Int2) {
      this.mSurfaceTexture = param1SurfaceTexture;
      this.mIsFormatChanged = false;
      this.mWidth = 0;
      this.mHeight = 0;
      TextureRenderView.InternalSurfaceHolder internalSurfaceHolder = new TextureRenderView.InternalSurfaceHolder(this.mWeakRenderView.get(), param1SurfaceTexture, this);
      Iterator<IRenderView.IRenderCallback> iterator = this.mRenderCallbackMap.keySet().iterator();
      while (iterator.hasNext())
        ((IRenderView.IRenderCallback)iterator.next()).onSurfaceCreated(internalSurfaceHolder, 0, 0); 
    }
    
    public boolean onSurfaceTextureDestroyed(SurfaceTexture param1SurfaceTexture) {
      this.mSurfaceTexture = param1SurfaceTexture;
      this.mIsFormatChanged = false;
      this.mWidth = 0;
      this.mHeight = 0;
      TextureRenderView.InternalSurfaceHolder internalSurfaceHolder = new TextureRenderView.InternalSurfaceHolder(this.mWeakRenderView.get(), param1SurfaceTexture, this);
      Iterator<IRenderView.IRenderCallback> iterator = this.mRenderCallbackMap.keySet().iterator();
      while (iterator.hasNext())
        ((IRenderView.IRenderCallback)iterator.next()).onSurfaceDestroyed(internalSurfaceHolder); 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("onSurfaceTextureDestroyed: destroy: ");
      stringBuilder.append(this.mOwnSurfaceTexture);
      Log.d("TextureRenderView", stringBuilder.toString());
      return this.mOwnSurfaceTexture;
    }
    
    public void onSurfaceTextureSizeChanged(SurfaceTexture param1SurfaceTexture, int param1Int1, int param1Int2) {
      this.mSurfaceTexture = param1SurfaceTexture;
      this.mIsFormatChanged = true;
      this.mWidth = param1Int1;
      this.mHeight = param1Int2;
      TextureRenderView.InternalSurfaceHolder internalSurfaceHolder = new TextureRenderView.InternalSurfaceHolder(this.mWeakRenderView.get(), param1SurfaceTexture, this);
      Iterator<IRenderView.IRenderCallback> iterator = this.mRenderCallbackMap.keySet().iterator();
      while (iterator.hasNext())
        ((IRenderView.IRenderCallback)iterator.next()).onSurfaceChanged(internalSurfaceHolder, 0, param1Int1, param1Int2); 
    }
    
    public void onSurfaceTextureUpdated(SurfaceTexture param1SurfaceTexture) {}
    
    public void releaseSurfaceTexture(SurfaceTexture param1SurfaceTexture) {
      if (param1SurfaceTexture == null) {
        Log.d("TextureRenderView", "releaseSurfaceTexture: null");
      } else if (this.mDidDetachFromWindow) {
        if (param1SurfaceTexture != this.mSurfaceTexture) {
          Log.d("TextureRenderView", "releaseSurfaceTexture: didDetachFromWindow(): release different SurfaceTexture");
          param1SurfaceTexture.release();
        } else if (!this.mOwnSurfaceTexture) {
          Log.d("TextureRenderView", "releaseSurfaceTexture: didDetachFromWindow(): release detached SurfaceTexture");
          param1SurfaceTexture.release();
        } else {
          Log.d("TextureRenderView", "releaseSurfaceTexture: didDetachFromWindow(): already released by TextureView");
        } 
      } else if (this.mWillDetachFromWindow) {
        if (param1SurfaceTexture != this.mSurfaceTexture) {
          Log.d("TextureRenderView", "releaseSurfaceTexture: willDetachFromWindow(): release different SurfaceTexture");
          param1SurfaceTexture.release();
        } else if (!this.mOwnSurfaceTexture) {
          Log.d("TextureRenderView", "releaseSurfaceTexture: willDetachFromWindow(): re-attach SurfaceTexture to TextureView");
          setOwnSurfaceTexture(true);
        } else {
          Log.d("TextureRenderView", "releaseSurfaceTexture: willDetachFromWindow(): will released by TextureView");
        } 
      } else if (param1SurfaceTexture != this.mSurfaceTexture) {
        Log.d("TextureRenderView", "releaseSurfaceTexture: alive: release different SurfaceTexture");
        param1SurfaceTexture.release();
      } else if (!this.mOwnSurfaceTexture) {
        Log.d("TextureRenderView", "releaseSurfaceTexture: alive: re-attach SurfaceTexture to TextureView");
        setOwnSurfaceTexture(true);
      } else {
        Log.d("TextureRenderView", "releaseSurfaceTexture: alive: will released by TextureView");
      } 
    }
    
    public void removeRenderCallback(IRenderView.IRenderCallback param1IRenderCallback) {
      this.mRenderCallbackMap.remove(param1IRenderCallback);
    }
    
    public void setOwnSurfaceTexture(boolean param1Boolean) {
      this.mOwnSurfaceTexture = param1Boolean;
    }
    
    public void willDetachFromWindow() {
      Log.d("TextureRenderView", "willDetachFromWindow()");
      this.mWillDetachFromWindow = true;
    }
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/com/netopsun/ijkvideoview/widget/media/TextureRenderView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */