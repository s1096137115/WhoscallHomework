package tw.com.maxting.whoscallhomework.paging

/**
 * 列表状态
 */
sealed class ListStatus

/**
 * 初始化中
 */
class Initialize : ListStatus()

/**
 * 初始化成功
 */
class InitializeSuccess : ListStatus()

/**
 * 初始化错误
 */
class InitializeError(val throwable: Throwable) : ListStatus()

/**
 * 列表空
 */
class Empty : ListStatus()

/**
 * 列表往下加载更多中
 */
class LoadMoreIn : ListStatus()

/**
 * 列表往下加载成功
 */
class LoadMoreSuccess : ListStatus()

/**
 * 列表往下加载失败
 */
class LoadMoreError(val throwable: Throwable) : ListStatus()

/**
 * 列表往下已全部加载完毕
 */
class End : ListStatus()

/**
 * 列表往上加载更多中
 */
class AtFrontLoadMoreIn : ListStatus()

/**
 * 列表往上加载成功
 */
class AtFrontLoadMoreSuccess : ListStatus()

/**
 * 列表往上加载失败
 */
class AtFrontLoadMoreError(val throwable: Throwable) : ListStatus()

/**
 * 列表往上已全部加载完毕
 */
class AtFrontEnd : ListStatus()
