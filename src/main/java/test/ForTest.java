/**
 * Created by xuewei on 2017/9/30.
 */
public class ForTest {

    public static void main(String arg0[]){
        Integer array[]=new Integer[]{1,2,3,4,5};
        Integer array2[]=new Integer[]{1,2,3,4,5};

//        for(int i=0;i<array.length;i++){
//            for(int j=0;j<array2.length;j++){
//                if(j==2){
//                    break;
//                }
//            }
//        }

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array2.length;j++){
                if(j==2){
                    continue;
                }
            }
        }

    }

}
