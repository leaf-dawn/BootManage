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
            欢迎你，{{msg}}同学
          </template>
          <MenuItem name="1-1" @click.native="searchBook"><span>搜索书籍</span></MenuItem>
          <MenuItem name="1-2" @click.native="lookRecord"><span>借阅记录</span></MenuItem>
        </Submenu>
        <!--<Submenu name="2">
          <template slot="title">
            <Icon type="ios-keypad"></Icon>
            书籍管理
          </template>
          <MenuItem name="2-1"><span @click="lookBook">查看书籍</span></MenuItem>
          <MenuItem name="2-2">Option 2</MenuItem>
        </Submenu>
        <Submenu name="3">
          <template slot="title">
            <Icon type="ios-analytics"></Icon>
            网站管理
          </template>
          <MenuItem name="3-1">Option 1</MenuItem>
          <MenuItem name="3-2">Option 2</MenuItem>
        </Submenu>-->
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
          <template id="searchBook"></template>
          <template id="myRecord"></template>
          <component :is="currentView"></component>
        </div>
      </div>
      </Col>
    </Row>
  </div>
</template>
<script>
  import searchBook from '../components/SearchBook.vue'
  import myRecord from '../components/MyRecord.vue'
  import Button from 'iview/src/components/button/button'
  export default {
    name: 'Reader',
    data () {
      return {
        msg: '',
        one_nav: '主页',
        two_nav: '>',
        three_nav: '搜索书籍',
        currentView: 'searchBook'
      }
    },
    mounted () {
      this.msg = window.localStorage.getItem('username')
    },
    methods: {
      searchBook () {
        this.one_nav = '主页'
        this.two_nav = '>'
        this.three_nav = '搜索书籍'
        this.currentView = 'searchBook'
      },
      lookRecord () {
        this.one_nav = '主页'
        this.two_nav = '>'
        this.three_nav = '借阅记录'
        this.currentView = 'myRecord'
      },
      logout () {
        this.$router.replace('/')
      }
    },
    components: {
      Button,
      searchBook: searchBook,
      myRecord: myRecord
    }
  }
</script>
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    height: 100vh;
    width: 100vw;
    background: #f5f7f9;
    position: relative;
    margin-top:-60px;
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
