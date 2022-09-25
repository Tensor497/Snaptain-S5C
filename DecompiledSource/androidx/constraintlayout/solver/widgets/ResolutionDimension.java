package androidx.constraintlayout.solver.widgets;

public class ResolutionDimension extends ResolutionNode {
  float value = 0.0F;
  
  public void remove() {
    this.state = 2;
  }
  
  public void reset() {
    super.reset();
    this.value = 0.0F;
  }
  
  public void resolve(int paramInt) {
    if (this.state == 0 || this.value != paramInt) {
      this.value = paramInt;
      if (this.state == 1)
        invalidate(); 
      didResolve();
    } 
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/androidx/constraintlayout/solver/widgets/ResolutionDimension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */