package mvvm.whoami.mvvmfast.extension

import android.annotation.SuppressLint
import android.arch.lifecycle.*
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by XKL on 2018/8/10.
 *
 */

/*===============================================function：协程 异步操作============================================================*/

/**
 * 生命周期管理，当activity或fragment 销毁时 将调用 deferred.cancel()销毁协程
 */
internal class CoroutineLifecycleListener(private val deferred: Deferred<*>) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelCoroutine() {
        if (!deferred.isCancelled) {
            deferred.cancel()
        }
    }
}

internal val background = newFixedThreadPoolContext(2, "background")

/**
 * Creates a lazily started coroutine that runs <code>loader()</code>.
 * The coroutine is automatically cancelled using the CoroutineLifecycleListener.
 */
fun <T> LifecycleOwner.load(loader: suspend () -> T): Deferred<T> {
    val deferred = GlobalScope.async(context = background, start = CoroutineStart.LAZY) {
        loader()
    }
    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
    return deferred
}

/**
 * Extension function on <code>Deferred<T><code> that creates a launches a coroutine which
 * will call <code>await()</code> and pass the returned value to <code>block()</code>.
 */
infix fun <T> Deferred<T>.then(block: suspend (T) -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main) {
        try {
            block(this@then.await())
        } catch (e: Exception) {
            // Just log the exception to confirm when we get cancelled (Expect JobCancellationException)
            e.printStackTrace()
            throw e
        }
    }
}
/*===============================================function：协程 异步操作============================================================*/

/*===============================================function：ViewModel相关============================================================*/
inline fun <reified VM : ViewModel> FragmentActivity.getViewModel(): VM {
    return ViewModelProviders.of(this).get(VM::class.java)
}
inline fun <reified VM : ViewModel> Fragment.getViewModel(): VM {
    return ViewModelProviders.of(this).get(VM::class.java)
}

inline fun <reified VM : ViewModel> FragmentActivity.getViewModel(id: String): VM {
    return ViewModelProviders.of(this).get(id, VM::class.java)
}
/*===============================================function：ViewModel相关============================================================*/

//安全转换
inline fun <reified T> Any.safeCast(action: T.() -> Unit) {
    if (this is T) {
        this.action()
    }
}

