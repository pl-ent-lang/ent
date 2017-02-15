/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class jrapl_EnergyCheckUtils */

#ifndef _Included_jrapl_EnergyCheckUtils
#define _Included_jrapl_EnergyCheckUtils
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    scale
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_jrapl_EnergyCheckUtils_scale
  (JNIEnv *, jclass, jint);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    freqAvailable
 * Signature: ()[I
 */
JNIEXPORT jintArray JNICALL Java_jrapl_EnergyCheckUtils_freqAvailable
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    GetPackagePowerSpec
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_jrapl_EnergyCheckUtils_GetPackagePowerSpec
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    GetDramPowerSpec
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_jrapl_EnergyCheckUtils_GetDramPowerSpec
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    SetPackagePowerLimit
 * Signature: (IID)V
 */
JNIEXPORT void JNICALL Java_jrapl_EnergyCheckUtils_SetPackagePowerLimit
  (JNIEnv *, jclass, jint, jint, jdouble);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    SetPackageTimeWindowLimit
 * Signature: (IID)V
 */
JNIEXPORT void JNICALL Java_jrapl_EnergyCheckUtils_SetPackageTimeWindowLimit
  (JNIEnv *, jclass, jint, jint, jdouble);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    SetDramTimeWindowLimit
 * Signature: (IID)V
 */
JNIEXPORT void JNICALL Java_jrapl_EnergyCheckUtils_SetDramTimeWindowLimit
  (JNIEnv *, jclass, jint, jint, jdouble);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    SetDramPowerLimit
 * Signature: (IID)V
 */
JNIEXPORT void JNICALL Java_jrapl_EnergyCheckUtils_SetDramPowerLimit
  (JNIEnv *, jclass, jint, jint, jdouble);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    ProfileInit
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_jrapl_EnergyCheckUtils_ProfileInit
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    GetSocketNum
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_jrapl_EnergyCheckUtils_GetSocketNum
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    EnergyStatCheck
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jrapl_EnergyCheckUtils_EnergyStatCheck
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    ProfileDealloc
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jrapl_EnergyCheckUtils_ProfileDealloc
  (JNIEnv *, jclass);

/*
 * Class:     jrapl_EnergyCheckUtils
 * Method:    SetPowerLimit
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_jrapl_EnergyCheckUtils_SetPowerLimit
  (JNIEnv *, jclass, jint);

#ifdef __cplusplus
}
#endif
#endif