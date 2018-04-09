### 1、用户登陆认证
| param | type | required | desc|
| --- | --- | --- | --- |
|username|String| Y | 姓名
|password|String| Y | 密码
|serviceUrl| String | Y |服务回调地址
* url：/account/token
* method：POST
* result:
```json
success:
{
    "code": 200,
    "message": "OK",
    "data": {
        "serviceUrl": "xxx",
        "expire": 3600,
        "token": "sfsfssdfsddfs-dsfsdfsdfsfsdf-sdfdsfsdfsd"
    }
}

error：
{
    "code": "1001",
    "message": "用户名密码不正确"
}
```

### 2、用户登陆状态检测
* url：/account/state
* method：GET
* result:
```json
success:
{
    "code": 200,
    "message": "OK",
    "data": {
        "serviceUrl": "xxx",
        "expire": 3600,
        "token": "sfsfssdfsddfs-dsfsdfsdfsfsdf-sdfdsfsdfsd"
    }
}

error:
{
    "code": 1002,
    "message": "用户未登录",
}
```

### 3、用户登陆token验证
* url：/account/token/state
* method：get
* result:
```json
success：
{
    "code": 200,
    "message": "OK",
    "data": {
        "expire": 3600,
        "available": true,
        "userId": "xxx",
        "token": "sfsfssdfsddfs-dsfsdfsdfsfsdf-sdfdsfsdfsd"
    }
}

error：
{
    "code": 1003,
    "message": "token已失效",
}

```


### 4、用户注销
* url：/account/token
* method：delete
* result:
```json
success：
{
    "code": 200,
    "message": "OK"
}
```




