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
            欢迎您，图书管理员:{{msg}}
          </template>
          <MenuItem name="1-1" @click.native="lookBook"><span>图书管理</span></MenuItem>
          <MenuItem name="1-2" @click.native="lookRecord"><span>借阅记录</span></MenuItem>
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
          <template id="bookManage"></template>
          <template id="recordManage"></template>
          <component :is="currentView"></component>
        </div>
      </div>
      </Col>
    </Row>
  </div>
</template>
<script>
  import bookManage from '../components/BookManage.vue'
  import recordManage from '../components/RecordManage.vue'
  import Button from 'iview/src/components/button/button'
  export default {
    name: 'Manager',
    data () {
      return {
        msg: 'haha',
        one_nav: '主页',
        two_nav: '后台管理',
        three_nav: '图书管理',
        currentView: 'bookManage'
      }
    },
    mounted () {
      this.msg = window.localStorage.getItem('username')
    },
    methods: {
      lookBook () {
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '图书管理'
        this.currentView = 'bookManage'
      },
      lookRecord () {
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '借阅记录'
        this.currentView = 'recordManage'
      },
      logout () {
        this.$router.replace('/')
      }
    },
    components: {
      Button,
      bookManage: bookManage,
      recordManage: recordManage
    }
  }
</script>
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    margin-top:-60px;
    width: 100vw;
    height: 100vh;
  }
  .flex {
    height: 100%;
    width: 100%;
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
  .layout-menu-left{
    background: #464c5b;
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
    color: white;
  }
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
