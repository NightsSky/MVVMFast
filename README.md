##  应急指挥设计文档

								更新时间：2021/10/09


### 一、	整体框架选择
#### 1.1、技术整体选型
- 开发语言：Kotlin
- 整体架构：MVVM
#### 1.2、主要第三方依赖
- 网络请求：[rxhttp](https://github.com/liujingxing/rxhttp/blob/master/README_zh.md) 
- 屏幕适配：[AndroidAutoSize](https://github.com/JessYanCoding/AndroidAutoSize)
- 列表适配器：[BRAVH](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)
- 常用工具：[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode/blob/master/lib/utilcode/README-CN.md)
- 弹框：[XPopup](https://github.com/li-xiaojun/XPopup)
- 图片加载：[coil](https://github.com/coil-kt/coil/blob/main/README-zh.md)
- 状态栏工具：[immersionbar](https://github.com/gyf-dev/ImmersionBar)
### 二、项目结构


```
├── app
│   └── application
├── base  自定义基类
│   
├── utils kotlin扩展、工具类   
│
├── models 数据、实体类封装
│   ├── api  项目api接口
│   ├── constant  存放常量
│   ├── entity  data class 类 ，按模块区别
│
│
├── services
│   ├──    广播、服务类
│
└── view  项目界面
│   ├── home 按模块功能分类
│       ├── adapter
│       ├── dialog
        ├── viewModel
│       └── activity
│   ├── other...
│
├── weight  自定义view、控件
│  
```

### 三、软件支持

---

- 开发环境： Android Studio Arctic Fox
- 支持版本：--
  

### 四、	详细设计

--略