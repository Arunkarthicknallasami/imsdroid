/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 1.3.39
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.doubango.tinyWRAP;

class tinyWRAPJNI {
  public final static native long new_SipUri(String jarg1);
  public final static native void delete_SipUri(long jarg1);
  public final static native boolean SipUri_isValid__SWIG_0(String jarg1);
  public final static native boolean SipUri_isValid__SWIG_1(long jarg1, SipUri jarg1_);
  public final static native long new_SipEvent();
  public final static native void delete_SipEvent(long jarg1);
  public final static native short SipEvent_getCode(long jarg1, SipEvent jarg1_);
  public final static native String SipEvent_getPhrase(long jarg1, SipEvent jarg1_);
  public final static native long SipEvent_getBaseSession(long jarg1, SipEvent jarg1_);
  public final static native long new_SipSession(long jarg1, SipStack jarg1_);
  public final static native void delete_SipSession(long jarg1);
  public final static native boolean SipSession_addHeader(long jarg1, SipSession jarg1_, String jarg2, String jarg3);
  public final static native boolean SipSession_removeHeader(long jarg1, SipSession jarg1_, String jarg2);
  public final static native boolean SipSession_addCaps__SWIG_0(long jarg1, SipSession jarg1_, String jarg2, String jarg3);
  public final static native boolean SipSession_addCaps__SWIG_1(long jarg1, SipSession jarg1_, String jarg2);
  public final static native boolean SipSession_removeCaps(long jarg1, SipSession jarg1_, String jarg2);
  public final static native boolean SipSession_setExpires(long jarg1, SipSession jarg1_, long jarg2);
  public final static native boolean SipSession_setFromUri(long jarg1, SipSession jarg1_, String jarg2);
  public final static native boolean SipSession_setToUri(long jarg1, SipSession jarg1_, String jarg2);
  public final static native boolean SipSession_setPayload(long jarg1, SipSession jarg1_, String jarg2, long jarg3);
  public final static native long new_RegistrationEvent();
  public final static native void delete_RegistrationEvent(long jarg1);
  public final static native int RegistrationEvent_getType(long jarg1, RegistrationEvent jarg1_);
  public final static native long RegistrationEvent_getSession(long jarg1, RegistrationEvent jarg1_);
  public final static native long new_RegistrationSession(long jarg1, SipStack jarg1_);
  public final static native void delete_RegistrationSession(long jarg1);
  public final static native boolean RegistrationSession_Register(long jarg1, RegistrationSession jarg1_);
  public final static native boolean RegistrationSession_UnRegister(long jarg1, RegistrationSession jarg1_);
  public final static native long new_SubscriptionEvent();
  public final static native void delete_SubscriptionEvent(long jarg1);
  public final static native int SubscriptionEvent_getType(long jarg1, SubscriptionEvent jarg1_);
  public final static native long SubscriptionEvent_getSession(long jarg1, SubscriptionEvent jarg1_);
  public final static native long new_SubscriptionSession(long jarg1, SipStack jarg1_);
  public final static native void delete_SubscriptionSession(long jarg1);
  public final static native boolean SubscriptionSession_Subscribe(long jarg1, SubscriptionSession jarg1_);
  public final static native boolean SubscriptionSession_UnSubscribe(long jarg1, SubscriptionSession jarg1_);
  public final static native long new_SipCallback();
  public final static native void delete_SipCallback(long jarg1);
  public final static native int SipCallback_OnRegistrationChanged(long jarg1, SipCallback jarg1_, long jarg2, RegistrationEvent jarg2_);
  public final static native int SipCallback_OnRegistrationChangedSwigExplicitSipCallback(long jarg1, SipCallback jarg1_, long jarg2, RegistrationEvent jarg2_);
  public final static native int SipCallback_OnSubscriptionChanged(long jarg1, SipCallback jarg1_, long jarg2, SubscriptionEvent jarg2_);
  public final static native int SipCallback_OnSubscriptionChangedSwigExplicitSipCallback(long jarg1, SipCallback jarg1_, long jarg2, SubscriptionEvent jarg2_);
  public final static native void SipCallback_director_connect(SipCallback obj, long cptr, boolean mem_own, boolean weak_global);
  public final static native void SipCallback_change_ownership(SipCallback obj, long cptr, boolean take_or_release);
  public final static native long new_SipDebugCallback();
  public final static native void delete_SipDebugCallback(long jarg1);
  public final static native int SipDebugCallback_OnDebugInfo(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugInfoSwigExplicitSipDebugCallback(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugWarn(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugWarnSwigExplicitSipDebugCallback(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugError(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugErrorSwigExplicitSipDebugCallback(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugFatal(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native int SipDebugCallback_OnDebugFatalSwigExplicitSipDebugCallback(long jarg1, SipDebugCallback jarg1_, String jarg2);
  public final static native void SipDebugCallback_director_connect(SipDebugCallback obj, long cptr, boolean mem_own, boolean weak_global);
  public final static native void SipDebugCallback_change_ownership(SipDebugCallback obj, long cptr, boolean take_or_release);
  public final static native long new_SafeObject();
  public final static native void delete_SafeObject(long jarg1);
  public final static native int SafeObject_Lock(long jarg1, SafeObject jarg1_);
  public final static native int SafeObject_UnLock(long jarg1, SafeObject jarg1_);
  public final static native long new_SipStack(long jarg1, SipCallback jarg1_, String jarg2, String jarg3, String jarg4);
  public final static native void delete_SipStack(long jarg1);
  public final static native boolean SipStack_start(long jarg1, SipStack jarg1_);
  public final static native boolean SipStack_setDebugCallback(long jarg1, SipStack jarg1_, long jarg2, SipDebugCallback jarg2_);
  public final static native boolean SipStack_setRealm(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_setIMPI(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_setIMPU(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_setPassword(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_setProxyCSCF(long jarg1, SipStack jarg1_, String jarg2, long jarg3, String jarg4, String jarg5);
  public final static native boolean SipStack_setLocalIP(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_setLocalPort(long jarg1, SipStack jarg1_, long jarg2);
  public final static native boolean SipStack_addHeader(long jarg1, SipStack jarg1_, String jarg2, String jarg3);
  public final static native boolean SipStack_removeHeader(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_addDnsServer(long jarg1, SipStack jarg1_, String jarg2);
  public final static native boolean SipStack_isValid(long jarg1, SipStack jarg1_);
  public final static native boolean SipStack_stop(long jarg1, SipStack jarg1_);
  public final static native long SWIGRegistrationEventUpcast(long jarg1);
  public final static native long SWIGRegistrationSessionUpcast(long jarg1);
  public final static native long SWIGSubscriptionEventUpcast(long jarg1);
  public final static native long SWIGSubscriptionSessionUpcast(long jarg1);
  public final static native long SWIGSipStackUpcast(long jarg1);

  public static int SwigDirector_SipCallback_OnRegistrationChanged(SipCallback self, long e) {
    return self.OnRegistrationChanged((e == 0) ? null : new RegistrationEvent(e, false));
  }
  public static int SwigDirector_SipCallback_OnSubscriptionChanged(SipCallback self, long e) {
    return self.OnSubscriptionChanged((e == 0) ? null : new SubscriptionEvent(e, false));
  }
  public static int SwigDirector_SipDebugCallback_OnDebugInfo(SipDebugCallback self, String message) {
    return self.OnDebugInfo(message);
  }
  public static int SwigDirector_SipDebugCallback_OnDebugWarn(SipDebugCallback self, String message) {
    return self.OnDebugWarn(message);
  }
  public static int SwigDirector_SipDebugCallback_OnDebugError(SipDebugCallback self, String message) {
    return self.OnDebugError(message);
  }
  public static int SwigDirector_SipDebugCallback_OnDebugFatal(SipDebugCallback self, String message) {
    return self.OnDebugFatal(message);
  }

  private final static native void swig_module_init();
  static {
    swig_module_init();
  }
}
