* 目的: 一个基于FFmpeg视频转码 App 1.0 版本，名字叫【狗子】，英文名【Gouzi】。
* 时间: 2018.11.8 ~ 2019.2.8 / 3 months / 12 weeks
* 节奏: 每周一个小版本 version 0.1 0.2 0.3 ... 0.11 1.0，每周四发版本。
	* ver 0.4: 基本款：能用
		* ver 0.1 选视频
		* ver 0.2 整个集成 FFmpeg
		* ver 0.3 转码
		* ver 0.4 视频输出/Log展示
	* ver 0.8: FFmpeg 瘦身
		* ver 0.5 本地编译 FFmpeg 去掉不必要模块
		* ver 0.6 尝试直接调用 FFmpeg 库代码并适配 Android 端代码
		* ver 0.7 继续精简 FFmpeg 代码
		* ver 0.8 继续精简 Ffmpeg 代码
	* ver 1.0: 终极款：轻量
		* ver 0.9 - ver 1.0 继续精简 FFmpeg 代码

		
这个项目的难点不在于 Android 端，而在于 FFmpeg 的适配。由于 FFmpeg 没有 Android 版本，所以必须通过 JNI 方式配合 FFmpeg 编译的 so 包去配合使用。问题在于 FFmpeg 编译的 so 包体积非常大，全量编译包有将近 20M，这对于目前安装包只有 13M 的即刻来说是不可接受的。所以这个项目的大部分精力是用来精简 FFmpeg，计划从小版本 0.5 开始就做这部分。

精简的方式主要是自定义编译 FFmpeg 和代码层面对 FFmpeg 进行剪裁。第一部分相对简单，网上也有很多教程。难的是第二种方式，因为这里需要阅读 FFmpeg 源码，而 FFmpeg 几乎都是 c/c++ 写的，我上一次看 c 代码还是在大学时候。但第一种的精简大小很有限，必须尝试第二种。写这个开发计划的目的就是为了督促我知难而上，这部分代码也会放在我 github 的公开库上面，这么做的目的也是为了防止我半途而废，希望最后不要打脸🙏🙏🙏。