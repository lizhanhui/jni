#include "jni.h"
#include "org_example_App.h"

#include <chrono>
#include <iostream>
#include <ratio>
#include <thread>

JNIEXPORT void JNICALL Java_org_example_App_foo(JNIEnv *env, jobject obj) {
  jobject global = env->NewGlobalRef(obj);
  env->DeleteGlobalRef(global);
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
  std::cout << "OnLoad" << std::endl;

  auto task = [=]() {
    JNIEnv *env;
    vm->AttachCurrentThreadAsDaemon(reinterpret_cast<void**>(&env), nullptr);
    while (true) {
      // std::this_thread::sleep_for(std::chrono::milliseconds(100));
    }
  };

  std::thread t(task);
  t.detach();

  return JNI_VERSION_1_8;
}