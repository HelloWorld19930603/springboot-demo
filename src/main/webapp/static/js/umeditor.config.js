/**
 *  umeditor����������
 *  �������������������༭��������
 */
/**************************��ʾ********************************
 * ���б�ע�͵��������ΪUEditorĬ��ֵ��
 * �޸�Ĭ������������ȷ���Ѿ���ȫ��ȷ�ò�������ʵ��;��
 * ��Ҫ�������޸ķ�����һ����ȡ���˴�ע�ͣ�Ȼ���޸ĳɶ�Ӧ��������һ������ʵ�����༭��ʱ�����Ӧ������
 * �������༭��ʱ����ֱ��ʹ�þɰ������ļ��滻�°������ļ�,���õ��ľɰ������ļ�����ȱ���¹�������Ĳ��������½ű�����
 **************************��ʾ********************************/


(function () {
    /**
     * �༭����Դ�ļ���·����������ʾ�ĺ����ǣ��Ա༭��ʵ����ҳ��Ϊ��ǰ·����ָ��༭����Դ�ļ�����dialog���ļ��У���·����
     * ���ںܶ�ͬѧ��ʹ�ñ༭����ʱ����ֵ�����·�����⣬�˴�ǿ�ҽ�����ʹ��"�������վ��Ŀ¼�����·��"�������á�
     * "�������վ��Ŀ¼�����·��"Ҳ������б�ܿ�ͷ������"/myProject/umeditor/"������·����
     * ���վ�����ж������ͬһ�㼶��ҳ����Ҫʵ�����༭������������ͬһUEditor��ʱ�򣬴˴���URL���ܲ�������ÿ��ҳ��ı༭����
     * ��ˣ�UEditor�ṩ����Բ�ͬҳ��ı༭���ɵ������õĸ�·����������˵������Ҫʵ�����༭����ҳ�����д�����´��뼴�ɡ���Ȼ����Ҫ��˴���URL���ڶ�Ӧ�����á�
     * window.UMEDITOR_HOME_URL = "/xxxx/xxxx/";
     */
    var URL = window.UMEDITOR_HOME_URL || (function(){

        function PathStack() {

            this.documentURL = self.document.URL || self.location.href;

            this.separator = '/';
            this.separatorPattern = /\\|\//g;
            this.currentDir = './';
            this.currentDirPattern = /^[.]\/]/;

            this.path = this.documentURL;
            this.stack = [];

            this.push( this.documentURL );

        }

        PathStack.isParentPath = function( path ){
            return path === '..';
        };

        PathStack.hasProtocol = function( path ){
            return !!PathStack.getProtocol( path );
        };

        PathStack.getProtocol = function( path ){

            var protocol = /^[^:]*:\/*/.exec( path );

            return protocol ? protocol[0] : null;

        };

        PathStack.prototype = {
            push: function( path ){

                this.path = path;

                update.call( this );
                parse.call( this );

                return this;

            },
            getPath: function(){
                return this + "";
            },
            toString: function(){
                return this.protocol + ( this.stack.concat( [''] ) ).join( this.separator );
            }
        };

        function update() {

            var protocol = PathStack.getProtocol( this.path || '' );

            if( protocol ) {

                //��Э��
                this.protocol = protocol;

                //local
                this.localSeparator = /\\|\//.exec( this.path.replace( protocol, '' ) )[0];

                this.stack = [];
            } else {
                protocol = /\\|\//.exec( this.path );
                protocol && (this.localSeparator = protocol[0]);
            }

        }

        function parse(){

            var parsedStack = this.path.replace( this.currentDirPattern, '' );

            if( PathStack.hasProtocol( this.path ) ) {
                parsedStack = parsedStack.replace( this.protocol , '');
            }

            parsedStack = parsedStack.split( this.localSeparator );
            parsedStack.length = parsedStack.length - 1;

            for(var i= 0,tempPath,l=parsedStack.length,root = this.stack;i<l;i++){
                tempPath = parsedStack[i];
                if(tempPath){
                    if( PathStack.isParentPath( tempPath ) ) {
                        root.pop();
                    } else {
                        root.push( tempPath );
                    }
                }

            }


        }

        var currentPath = document.getElementsByTagName('script');

        currentPath = currentPath[ currentPath.length -1 ].src;

        return new PathStack().push( currentPath ) + "";


    })();

    /**
     * ���������塣ע�⣬�˴������漰��·�������ñ���©URL������
     */
    window.UMEDITOR_CONFIG = {

        //Ϊ�༭��ʵ�����һ��·����������ܱ�ע��
        UMEDITOR_HOME_URL : URL

        //ͼƬ�ϴ�������
        ,imageUrl:"imageUp"             //ͼƬ�ϴ��ύ��ַ
        ,imagePath: "/images/home/college/"                     //ͼƬ������ַ��������fixedImagePath,�����������󣬿���������
        ,imageFieldName:"upfile"                   //ͼƬ���ݵ�key,���˴��޸ģ���Ҫ�ں�̨��Ӧ�ļ��޸Ķ�Ӧ����


        //�������ϵ����еĹ��ܰ�ť�������򣬿�����new�༭����ʵ��ʱѡ���Լ���Ҫ�Ĵ��¶���
        ,toolbar:[
            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | emotion image video  | map',
            '| horizontal print preview fullscreen', 'drafts', 'formula'
        ]

        //����������,Ĭ����zh-cn������Ҫ�Ļ�Ҳ����ʹ�����������ķ�ʽ���Զ��������л�����Ȼ��ǰ��������lang�ļ����´��ڶ�Ӧ�������ļ���
        //langֵҲ����ͨ���Զ���ȡ (navigator.language||navigator.browserLanguage ||navigator.userLanguage).toLowerCase()
        //,lang:"zh-cn"
        //,langPath:URL +"lang/"

        //ie�µ������Զ����
        //,autourldetectinie:false

        //����������,Ĭ����default������Ҫ�Ļ�Ҳ����ʹ�����������ķ�ʽ���Զ��������л�����Ȼ��ǰ��������themes�ļ����´��ڶ�Ӧ�������ļ���
        //��������Ƥ��:default
        //,theme:'default'
        //,themePath:URL +"themes/"



        //���getAllHtml���������ڶ�Ӧ��head��ǩ�����Ӹñ������á�
        //,charset:"utf-8"

        //����������Ŀ
        //,isShow : true    //Ĭ����ʾ�༭��

        //,initialContent:'��ӭʹ��UMEDITOR!'    //��ʼ���༭��������,Ҳ����ͨ��textarea/script��ֵ������������

        //,initialFrameWidth:500 //��ʼ���༭�����,Ĭ��500
        //,initialFrameHeight:500  //��ʼ���༭���߶�,Ĭ��500

        //,autoClearinitialContent:true //�Ƿ��Զ�����༭����ʼ���ݣ�ע�⣺���focus��������Ϊtrue,���ҲΪ�棬��ô�༭��һ�����ͻᴥ�����³�ʼ�������ݿ�������

        //,textarea:'editorValue' // �ύ��ʱ����������ȡ�༭���ύ���ݵ����õĲ�������ʵ��ʱ���Ը�����name���ԣ��Ὣname������ֵ��Ϊÿ��ʵ���ļ�ֵ������ÿ��ʵ������ʱ���������ֵ

        //,focus:false //��ʼ��ʱ���Ƿ��ñ༭����ý���true��false

        //,autoClearEmptyNode : true //getContentʱ���Ƿ�ɾ���յ�inlineElement�ڵ㣨����Ƕ�׵������

        //,fullscreen : false //�Ƿ�����ʼ��ʱ��ȫ����Ĭ�Ϲر�

        //,readonly : false //�༭����ʼ��������,�༭�����Ƿ���ֻ���ģ�Ĭ����false

        //,zIndex : 900     //�༭���㼶�Ļ���,Ĭ����900

        //����Զ��壬��ø�p��ǩ���µ��иߣ�Ҫ����������ʱ������������
        //ע��������ӵ���ʽ����÷���.edui-editor-body .edui-body-container���������±ߣ���ֹ��ҳ����css��ͻ
        //,initialStyle:'.edui-editor-body .edui-body-container p{line-height:1em}'

        //,autoSyncData:true //�Զ�ͬ���༭��Ҫ�ύ������

        //,emotionLocalization:false //�Ƿ������鱾�ػ���Ĭ�Ϲرա���Ҫ������ȷ��emotion�ļ����°��������ṩ��images�����ļ���

        //,allHtmlEnabled:false //�ύ����̨�������Ƿ��������html�ַ���

        //fontfamily
        //��������
//        ,'fontfamily':[
//              { name: 'songti', val: '����,SimSun'},
//          ]

        //fontsize
        //�ֺ�
        //,'fontsize':[10, 11, 12, 14, 16, 18, 20, 24, 36]

        //paragraph
        //�����ʽ ֵ����ʱ֧�ֶ������Զ�ʶ�������ã���������ֵΪ׼
        //,'paragraph':{'p':'', 'h1':'', 'h2':'', 'h3':'', 'h4':'', 'h5':'', 'h6':''}

        //undo
        //���������˵Ĵ���,Ĭ��20
        //,maxUndoCount:20
        //��������ַ���������ֵʱ������һ���ֳ�
        //,maxInputCount:1

        //imageScaleEnabled
        // �Ƿ��������ļ���ק�ı��С,Ĭ��true
        //,imageScaleEnabled:true

        //dropFileEnabled
        // �Ƿ������Ϸ�ͼƬ���༭�����ϴ�������,Ĭ��true
        //,dropFileEnabled:true

        //pasteImageEnabled
        // �Ƿ�����ճ��QQ�������ϴ�������,Ĭ��true
        //,pasteImageEnabled:true

        //autoHeightEnabled
        // �Ƿ��Զ�����,Ĭ��true
        //,autoHeightEnabled:true

        //autoFloatEnabled
        //�Ƿ񱣳�toolbar��λ�ò���,Ĭ��true
        //,autoFloatEnabled:true

        //����ʱ��������������������ĸ߶ȣ�����ĳЩ���й̶�ͷ����ҳ��
        //,topOffset:30

        //��д���˹���
        ,filterRules: {}
        // xss �����Ƿ���,inserthtml�Ȳ���
 		,xssFilterRules: true
 		//input xss����
 		,inputXssFilter: true
 		//output xss����
 		,outputXssFilter: true
 		// xss���˰����� ������Դ: https://raw.githubusercontent.com/leizongmin/js-xss/master/lib/default.js
        ,whiteList: {
            a:      ['target', 'href', 'title', 'style', 'class', 'id'],
			abbr:   ['title', 'style', 'class', 'id'],
			address: ['style', 'class', 'id'],
			area:   ['shape', 'coords', 'href', 'alt', 'style', 'class', 'id'],
			article: ['style', 'class', 'id'],
			aside:  ['style', 'class', 'id'],
			audio:  ['autoplay', 'controls', 'loop', 'preload', 'src', 'style', 'class', 'id'],
			b:      ['style', 'class', 'id'],
			bdi:    ['dir'],
			bdo:    ['dir'],
			big:    [],
			blockquote: ['cite', 'style', 'class', 'id'],
			br:     [],
			caption: ['style', 'class', 'id'],
			center: [],
			cite:   [],
			code:   ['style', 'class', 'id'],
			col:    ['align', 'valign', 'span', 'width', 'style', 'class', 'id'],
			colgroup: ['align', 'valign', 'span', 'width', 'style', 'class', 'id'],
			dd:     ['style', 'class', 'id'],
			del:    ['datetime', 'style', 'class', 'id'],
			details: ['open', 'style', 'class', 'id'],
			div:    ['style', 'class', 'id'],
			dl:     ['style', 'class', 'id'],
			dt:     ['style', 'class', 'id'],
			em:     ['style', 'class', 'id'],
            embed:  ['style', 'class', 'id', '_url', 'type', 'pluginspage', 'src', 'width', 'height', 'wmode', 'play', 'loop', 'menu', 'allowscriptaccess', 'allowfullscreen'],
			font:   ['color', 'size', 'face', 'style', 'class', 'id'],
			footer: ['style', 'class', 'id'],
			h1:     ['style', 'class', 'id'],
			h2:     ['style', 'class', 'id'],
			h3:     ['style', 'class', 'id'],
			h4:     ['style', 'class', 'id'],
			h5:     ['style', 'class', 'id'],
			h6:     ['style', 'class', 'id'],
			header: ['style', 'class', 'id'],
			hr:     ['style', 'class', 'id'],
			i:      ['style', 'class', 'id'],
            iframe: ['style', 'class', 'id', 'src', 'frameborder', 'data-latex'],
			img:    ['src', 'alt', 'title', 'width', 'height', 'style', 'class', 'id', '_url'],
			ins:    ['datetime', 'style', 'class', 'id'],
			li:     ['style', 'class', 'id'],
			mark:   [],
			nav:    [],
			ol:     ['style', 'class', 'id'],
			p:      ['style', 'class', 'id'],
			pre:    ['style', 'class', 'id'],
			s:      [],
			section:[],
			small:  ['style', 'class', 'id'],
			span:   ['style', 'class', 'id'],
			sub:    ['style', 'class', 'id'],
			sup:    ['style', 'class', 'id'],
			strong: ['style', 'class', 'id'],
			table:  ['width', 'border', 'align', 'valign', 'style', 'class', 'id'],
			tbody:  ['align', 'valign', 'style', 'class', 'id'],
			td:     ['width', 'rowspan', 'colspan', 'align', 'valign', 'style', 'class', 'id'],
			tfoot:  ['align', 'valign', 'style', 'class', 'id'],
			th:     ['width', 'rowspan', 'colspan', 'align', 'valign', 'style', 'class', 'id'],
			thead:  ['align', 'valign', 'style', 'class', 'id'],
			tr:     ['rowspan', 'align', 'valign', 'style', 'class', 'id'],
			tt:     ['style', 'class', 'id'],
			u:      [],
			ul:     ['style', 'class', 'id'],
            svg:    ['style', 'class', 'id', 'width', 'height', 'xmlns', 'fill', 'viewBox'],
			video:  ['autoplay', 'controls', 'loop', 'preload', 'src', 'height', 'width', 'style', 'class', 'id']
        }
    };
})();
