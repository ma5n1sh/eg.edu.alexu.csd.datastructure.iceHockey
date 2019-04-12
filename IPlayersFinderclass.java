//ahmed yasser ahmed 10
public class IPlayersFinderclass implements IPlayersFinder {


        public  boolean recursivemaskfiller(char[][] arr,int[][]  mask,int y,int x,int i,char searched){
            if(arr[y][x]==searched){
                if(mask[y][x]==0){
                    mask[y][x]=i;
                    if(y+1<arr.length) {
                        recursivemaskfiller(arr, mask, y + 1, x, i, searched);
                    }
                    if(y-1>-1) {
                        recursivemaskfiller(arr, mask, y - 1, x, i, searched);
                    }
                    if(x+1<arr[y].length) {
                        recursivemaskfiller(arr, mask, y, x+1, i, searched);
                    }
                    if(x-1>-1) {
                        recursivemaskfiller(arr, mask, y , x-1, i, searched);
                    }
                    return true;

                }

            }

            return false;

        }

        public  void  bubblesort2d(int a[][]){
            int temp;
            for(int i=0;i<a.length;i++){
                for(int j=0;j<a.length-1-i;j++){
                    if(a[j][0]>a[j+1][0]){
                        temp=a[j][0];a[j][0]=a[j+1][0];a[j+1][0]=temp;
                        temp=a[j][1];a[j][1]=a[j+1][1];a[j+1][1]=temp;
                    }
                    else if(a[j][0]==a[j+1][0]){
                        if(a[j][1]>a[j+1][1]){
                            temp=a[j][0];a[j][0]=a[j+1][0];a[j+1][0]=temp;
                            temp=a[j][1];a[j][1]=a[j+1][1];a[j+1][1]=temp;
                        }
                    }
                }
            }

        }

        public  java.awt.Point[] findPlayers(String[] photo, int team, int threshold){
            char[][] arr=new char[photo.length][photo[0].toCharArray().length];
            for(int i=0;i<photo.length;i++){
                arr[i]=photo[i].toCharArray();
            }
            char searched=(char)(48+team);
            int[][] mask=new int[arr.length][arr[0].length];
            int i=1;
            for(int y=0;y<arr.length;y++){
                for(int x=0;x<arr[0].length;x++){
                    if(recursivemaskfiller(arr, mask, y  , x, i, searched)){
                        i++;
                    }
                }
            }
            int[]available=new int[i+1];
            for(int k=0;k<=i;k++) {available[k]=k;}
            int flag=0,count;
            for(int k=1;k<=i;k++) {count=0;
                for (int z = 0; z < mask.length; z++) {
                    for (int j = 0; j < mask[z].length; j++) {
                        if(mask[z][j]==k){count++;}
                        if(flag==1){if(mask[z][j]==k-1){mask[z][j]=0;}}

                    }
                }
                if(count*4<threshold){flag=1;if(k!=i){available[k]=0;}}
                else {flag=0;}
            }
            count=0;
            for(int k=0;k<i;k++) {if(available[k]!=0){count++;}}
            int[][]points=new int[count+1][2];
            int index=0;
            for(int k=1;k<=i;k++) {if(available[k]==0){continue;}
                int largestx=0,largesty=0,smallestx=mask[0].length,smallesty=mask.length;
                for (int z = 0; z < mask.length; z++) {
                    for (int j = 0; j < mask[z].length; j++) {
                        if(mask[z][j]==k){
                            if(j>largestx){largestx=j;}
                            if(z>largesty){largesty=z;}
                            if(j<smallestx){smallestx=j;}
                            if(z<smallesty){smallesty=z;}

                        }
                    }

                }
                points[index][0]=smallestx+largestx+1;
                points[index][1]=smallesty+largesty+1;
                index++;
            }
            int[][]points2=new int[points.length-1][2];
            for(int j=0;j<points2.length;j++){
                points2[j]=points[j];
            }
            points=points2;
            bubblesort2d(points);
            java.awt.Point[] p=new java.awt.Point[points.length];
            for (int z=0;z<points.length;z++){
                p[z]=new java.awt.Point();
                p[z].x=points[z][0];
                p[z].y=points[z][1];
            }

            return p;
        }


}
