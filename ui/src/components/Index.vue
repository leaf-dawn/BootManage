<template>
  <div class="layout">
    <Row class="flex" type="flex">
      <Col span="5" class="layout-menu-left">
      <Menu active-name="1-1" theme="dark" width="auto" :open-names="['1']">
        <div class="layout-logo-left">
          <img class="icon" src="../assets/schoolBadge.png"><h1 class="book_text">广理图书馆</h1>
        </div>
        <Submenu name="1">
          <template slot="title">
            欢迎你，超級管理员
          </template>
          <MenuItem name="1-1" @click.native="lookUser"><span>用户管理</span></MenuItem>
        </Submenu>
      </Menu>
      </Col>
      <Col span="19">
      <div class="layout-breadcrumb">
        <Breadcrumb>
          <BreadcrumbItem href="#">{{one_nav}}</BreadcrumbItem>
          <BreadcrumbItem href="#">{{two_nav}}</BreadcrumbItem>
          <BreadcrumbItem>{{three_nav}}</BreadcrumbItem>
          <button id="logout" @click="logout()">退出</button>
        </Breadcrumb>
      </div>
      <div class="layout-content">
        <div class="layout-content-main">
          <template id="userManage"></template>
          <component :is="currentView"></component>
        </div>
      </div>
      </Col>
    </Row>
  </div>
</template>
<script>
  import userManage from '../components/UserManage.vue'
  import Button from 'iview/src/components/button/button'
  export default {
    name: 'Index',
    data () {
      return {
        msg: 'haha',
        one_nav: '主页',
        two_nav: '后台管理',
        three_nav: '用户管理',
        currentView: 'userManage'
      }
    },
    methods: {
      lookUser () {
        this.currentView = 'userManage'
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '用户管理'
        this.currentView = 'userManage'
      },
      logout () {
        this.$router.replace('/')
      }
    },
    components: {
      Button,
      userManage: userManage
    }
  }
</script>
<style scoped>
  .layout{
    position: relative;
    margin-top:-60px;
    height: 100vh;
    widows: 100wh;
  }
  .flex {
    height: 100vh;
    widows: 100wh;
  }
  .layout-breadcrumb{
    padding: 10px 15px 0;
    text-align:left;
  }
  .layout-content{
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
  }
  .layout-content-main{
    padding: 10px;
  }
  .layout-copy{
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }
  .layout-menu-left{
    background: #464c5b;
  }
  .layout-header{
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  .layout-logo-left{
    display: inline-flex;
    justify-content: center;
    width: 100%;
    height: 50px;
    margin-top: 10px;
  }
  .icon {
    height: 50px;
    width: 50px;
  }
  .book_text {
    color: white;  }
  Button{
    color:white;
  }
  
  /* 退出按钮 */
  #logout{
    position: absolute;
    right:5%;
    background-color:rgb(224, 74, 74);
    width: 70px;
    height: 30px;
    border: #464c5b;
    border-radius: 10%;
  }
</style>
