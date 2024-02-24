<template>
  <div class="container">
    <Form class="loginForm" ref="formItem" :model="formItem" :rules="ruleItem" :label-width="80">
      <Row>
        <Col :xs="23" :sm="23" :md="23" :lg="23">
        <h2>{{msg}}</h2>
        </Col>
      </Row>
      <Row>
        <Col :xs="23" :sm="23" :md="23" :lg="23">
        <FormItem prop="account" label="注册账号">
          <Input type="text" v-model="formItem.account" placeholder="填写注册邮箱"></Input>
        </FormItem>
        </Col>
      </Row>
      <Row>
        <Col :xs="23" :sm="23" :md="23" :lg="23">
        <FormItem prop="name" label="姓名">
          <Input type="text" v-model="formItem.name" placeholder="输入姓名"></Input>
        </FormItem>
        </Col>
      </Row>
      <Row>
        <Col :xs="23" :sm="23" :md="23" :lg="23">
        <FormItem prop="sex" label="性别" >
            <dev style="position: absolute; left: 20px;">
              <input type="radio" value="男" v-model="sex" style="margin-left: 5px;"><label>男</label>
              <input type="radio" value="女" v-model="sex" style="margin-left: 5px;"><label>女</label>
            </dev>
        </FormItem>
        </Col>
      </Row>
      <Row>
        <Col :xs="23" :sm="23" :md="23" :lg="23">
        <FormItem prop="password" label="注册密码">
          <Input type="password" v-model="formItem.password" placeholder="填写密码"></Input>
        </FormItem>
        </Col>
      </Row>
      <Row>
        <Col :xs="23" :sm="23" :md="23" :lg="23">
        <FormItem prop="conf_password" label="确认密码">
          <Input type="password" v-model="formItem.conf_password" placeholder="重复密码"></Input>
        </FormItem>
        </Col>
      </Row>
       <!-- 注册按钮 -->
      <br>
      <Button id="resgiter_btn" type="primary" @click="handleSubmit('formItem')">
          <span>注册</span>
      </Button>
        
      <Row class="tip">
        <Col :xs="10" :sm="10" :md="10" :lg="10" offset="12">
          <span class="login_font"  @click="login()"><a>已有账号？点击登陆</a></span>
        </Col>
      </Row>
    </Form>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      sex: '男',
      msg: '图书管理系统',
      verifyCode: 'hello',
      formItem: {
        account: '',
        password: '',
        sex: '男',
        name: ''
      },
      ruleItem: {
        account: [{
          required: true,
          message: '请填写账号！',
          trigger: 'blur'
        }],
        name: [{
          required: true,
          message: '请输入姓名！',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请填写密码',
          trigger: 'blur'
        }, {
          type: 'string',
          min: 3,
          message: '密码长度不能小于6位',
          trigger: 'blur'
        }],
        conf_password: [{
          required: true,
          message: '请填写密码',
          trigger: 'blur'
        }, {
          type: 'string',
          min: 3,
          message: '密码长度不能小于6位',
          trigger: 'blur'
        }]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      var that = this
      this.loading = true
      this.$refs[name].validate((valid) => {
        if (valid) {
          that.$http.post(that.GLOBAL.serverPath + '/user/register',
            {
              account: that.formItem.account,
              sex: that.formItem.sex,
              name: that.formItem.name,
              password: that.formItem.password
            },
            {
              emulateJSON: true
            }
          ).then(function (res) {
            if (res.data.status === 'ok') {
              this.$Message.success('注冊成功!')
              this.$router.replace({path: '/'})
            } else {
              this.$Message.error(res.data.message)
              this.loading = false
            }
          }, function() {
            this.$Message.error('网络错误!')
            this.loading = false
          })
        } else {
          this.loading = false
        }
      })
    },
    login () {
      this.$router.replace('/')
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .container{
    position : absolute;
    top: 0px;
    width : 100%;
    height : 100%;
    background-image: url('../assets/bk.png');
    background-position: center center;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;
  }
  .loginForm {
    position: relative;
    top: 10%;
    background-color: aliceblue;
  }
  h2{
    color:#0e4683;
    margin-bottom:20px;
  }
  Form{
    margin:0 auto;
    width: 380px;
    border: 1px solid rgb(192, 192, 192);
    padding:50px 0;
    /*background-color:red;*/
  }
  #login_btn{
    width:70%;
  }
  .tip{
    margin-top:10px;
    color:darkgrey;
  }

  /* 注册按钮 */
  #resgiter_btn{
    width:70%;
  }
</style>
