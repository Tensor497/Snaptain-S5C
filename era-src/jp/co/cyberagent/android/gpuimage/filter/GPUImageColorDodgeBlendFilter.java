package jp.co.cyberagent.android.gpuimage.filter;

public class GPUImageColorDodgeBlendFilter extends GPUImageTwoInputFilter {
  public static final String COLOR_DODGE_BLEND_FRAGMENT_SHADER = "precision mediump float;\n \n varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     vec4 base = texture2D(inputImageTexture, textureCoordinate);\n     vec4 overlay = texture2D(inputImageTexture2, textureCoordinate2);\n     \n     vec3 baseOverlayAlphaProduct = vec3(overlay.a * base.a);\n     vec3 rightHandProduct = overlay.rgb * (1.0 - base.a) + base.rgb * (1.0 - overlay.a);\n     \n     vec3 firstBlendColor = baseOverlayAlphaProduct + rightHandProduct;\n     vec3 overlayRGB = clamp((overlay.rgb / clamp(overlay.a, 0.01, 1.0)) * step(0.0, overlay.a), 0.0, 0.99);\n     \n     vec3 secondBlendColor = (base.rgb * overlay.a) / (1.0 - overlayRGB) + rightHandProduct;\n     \n     vec3 colorChoice = step((overlay.rgb * base.a + base.rgb * overlay.a), baseOverlayAlphaProduct);\n     \n     gl_FragColor = vec4(mix(firstBlendColor, secondBlendColor, colorChoice), 1.0);\n }";
  
  public GPUImageColorDodgeBlendFilter() {
    super("precision mediump float;\n \n varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     vec4 base = texture2D(inputImageTexture, textureCoordinate);\n     vec4 overlay = texture2D(inputImageTexture2, textureCoordinate2);\n     \n     vec3 baseOverlayAlphaProduct = vec3(overlay.a * base.a);\n     vec3 rightHandProduct = overlay.rgb * (1.0 - base.a) + base.rgb * (1.0 - overlay.a);\n     \n     vec3 firstBlendColor = baseOverlayAlphaProduct + rightHandProduct;\n     vec3 overlayRGB = clamp((overlay.rgb / clamp(overlay.a, 0.01, 1.0)) * step(0.0, overlay.a), 0.0, 0.99);\n     \n     vec3 secondBlendColor = (base.rgb * overlay.a) / (1.0 - overlayRGB) + rightHandProduct;\n     \n     vec3 colorChoice = step((overlay.rgb * base.a + base.rgb * overlay.a), baseOverlayAlphaProduct);\n     \n     gl_FragColor = vec4(mix(firstBlendColor, secondBlendColor, colorChoice), 1.0);\n }");
  }
}


/* Location:              /home/platinum/Documents/AndroidRE/com.guanxu.technology.snaptain_era_s5c_29_apps.evozi.com-dex2jar.jar!/jp/co/cyberagent/android/gpuimage/filter/GPUImageColorDodgeBlendFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */