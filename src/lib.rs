// This is the interface to the JVM that we'll call the majority of our
// methods on.
use jni::sys::{jint, JNI_VERSION_1_8};
use jni::{JNIEnv, JavaVM};
use std::sync::Arc;

// These objects are what you should use as arguments to your native
// function. They carry extra lifetime information to prevent them escaping
// this context and getting used after being GC'd.
use jni::objects::{JClass, JObject, JString};

// This is just a pointer. We'll be returning it from our function. We
// can't return one of the objects with lifetime information because the
// lifetime checker won't let us.
use jni::sys::jstring;

// JNIEXPORT void JNICALL Java_org_example_App_foo
//   (JNIEnv *, jobject);

// This keeps Rust from "mangling" the name and making it unique for this
// crate.
#[no_mangle]
pub extern "system" fn Java_org_example_App_foo<'local>(env: JNIEnv<'local>, obj: JObject<'local>) {
    env.new_global_ref(obj).unwrap();
}

#[no_mangle]
pub extern "system" fn JNI_OnLoad(vm: JavaVM, _: *mut libc::c_void) -> jint {
    let vm = Arc::new(vm);
    println!("JNI_OnLoad");
    std::thread::Builder::new()
        .name("Runtime".to_owned())
        .spawn(move || match vm.attach_current_thread_as_daemon() {
            Ok(env) => {
                println!("Attached and start to loop");
                loop {}
            }
            Err(_e) => {
                panic!("Failed to attach");
            }
        })
        .expect("Failed to spawn a thread");
    JNI_VERSION_1_8
}
