#include "org_example_App.h"

JNIEXPORT void JNICALL Java_org_example_App_foo
        (JNIEnv *env, jobject obj) {
    jobject global = env->NewGlobalRef(obj);
    env->DeleteGlobalRef(global);
}