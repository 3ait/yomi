<template>
  <div class="scan">
    <div @click="closeScan" class="close-btn">关闭</div>
    <p class="tip">{{ tip }}</p>
    <div class="barcode" id="bcid">
      <div style="height:100%"></div>
    </div>
  </div>
</template>

<script>
  let scan = null;

  export default {
    name: 'scanner',
    data() {
      return {
        tip: '...打开摄像头中...'
      };
    },
    created() {
      this.startRecognize();
    },
    methods: {
      // 创建扫描控件
      startRecognize() {
        const _this = this;
        setTimeout(() => {
          if (!window.plus) return;
          scan = new plus.barcode.Barcode('bcid');
          scan.onmarked = onmarked;
          function onmarked(type, result, file) {
            switch (type) {
              case plus.barcode.QR:
                type = 'QR';
                break;
              case plus.barcode.EAN13:
                type = 'EAN13';
                break;
              case plus.barcode.EAN8:
                type = 'EAN8';
                break;
              default:
                type = '其它' + type;
                break;
            }
            result = result.replace(/\n/g, '');
            const reg = /^[0-9]*$/;
            if (reg.test(result)) {
              _this.$router.push({ name: 'productDetail', params: { id: result }});
              scan.close();
            } else {
              alert('无法解析此二维码');
              _this.closeScan();
            }
          }
          _this.startScan();
        }, 500);
      },
      // 开始扫描
      startScan() {
        if (!window.plus) return;
        scan.start();
        this.tip = '';
      },
      // 关闭扫描
      cancelScan() {
        if (!window.plus) return;
        scan.cancel();
      },
      // 关闭条码识别控件
      closeScan() {
        if (!window.plus) return;
        scan.close();
        this.$router.push({ name: 'home' });
      }
    }
  };
</script>

<style lang="less">
  .scan {
    height: 100%;
    position: relative;
    .barcode {
      width: 100%;
      position: absolute;
      left: 0;
      right: 0;
      top: 48px;
      bottom: 0;
      text-align: center;
      color: #333333;
      background: #ffffff;
      z-index: 1;
    }
    footer {
      position: absolute;
      left: 0;
      bottom: 1rem;
      height: 2rem;
      line-height: 2rem;
      z-index: 2;
    }
    .tip{
      position: absolute;
      width: 100%;
      height: 24px;
      line-height: 24px;
      text-align: center;
      top: 48px;
      z-index: 2;
    }
    .close-btn{
      position: absolute;
      width: 50px;
      height: 48px;
      line-height: 48px;
      text-align: center;
      color: #333333;
      left: 20px;
      top: 0;
      z-index: 3;
    }
  }
</style>
