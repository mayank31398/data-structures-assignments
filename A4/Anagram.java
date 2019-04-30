import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Anagram
{
    int SIZE=100003;
    ArrayList<String>[] array=(ArrayList<String>[])new ArrayList[SIZE];
    int[] primes=new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157};
    ArrayList<String> FINAL=new ArrayList<String>();

    public static void main(String[] args) throws Exception
    {
        Anagram ana=new Anagram();
        BufferedReader vocabulary=new BufferedReader(new FileReader(args[0]));
        int vocabulary_size=Integer.parseInt(vocabulary.readLine());
        BufferedReader input=new BufferedReader(new FileReader(args[1]));
        int input_size=Integer.parseInt(input.readLine());
        String x;
        Word word;
        int Hash2;
        int Hash;
        int count;

        for(int i=0;i<vocabulary_size;i++)
        {
            x=vocabulary.readLine();

            if(x.length()>12)
                continue;

            count=1;
            word=ana.Hasher1(x);
            Hash=word.Hash1;
            Hash2=ana.Hasher2(x);

            while(ana.array[Hash]!=null && !(ana.SortedString(ana.array[Hash].get(0)).equals(word.SortedWord)))
            {
                Hash=(word.Hash1+count*Hash2)%ana.SIZE;
                count++;
            }

            if(ana.array[Hash]==null)
                ana.array[Hash]=new ArrayList<String>();

            ana.array[Hash].add(x);
        }

        vocabulary.close();

        for(int i=0;i<input_size;i++)
        {
            x=input.readLine();
            ana.CombinationList(x);

            Collections.sort(ana.FINAL);

            for(int j=0;j<ana.FINAL.size();j++)
                System.out.println(ana.FINAL.get(j));
            System.out.println(-1);

            ana.FINAL=new ArrayList<String>();
        }

        input.close();
    }

    public int SearchAnagrams(String x)
    {
        Word word=Hasher1(x);
        int Hash2=Hasher2(x);
        int Hash=word.Hash1;
        int count=1;

        while(array[Hash]!=null)
        {
            if(SortedString(array[Hash].get(0)).equals(word.SortedWord))
                return Hash;

            Hash=(word.Hash1+count*Hash2)%SIZE;
            count++;
        }

        return -1;
    }

    public void CombinationList(String x)
    {
        char[] characters=x.toCharArray();
        Arrays.sort(characters);
        String y=new String(characters);
        StringBuilder temp1=new StringBuilder();
        StringBuilder temp2=new StringBuilder();
        ArrayList<String> list1=new ArrayList<String>();
        ArrayList<String> list2=new ArrayList<String>();
        ArrayList<String> list3=new ArrayList<String>();
        ArrayList<String> list4=new ArrayList<String>();
        int n=x.length();
        String str1;
        String str2;

        if(3<=n && n<=12)
        {
            int index=SearchAnagrams(x);
            if(index!=-1)
                FINAL.addAll(array[index]);
        }

        if(n==6)
        {
            // 3,3
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),false);
        }
        else if(n==7)
        {
            // 3,4
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
        }
        else if(n==8)
        {
            // 3,5
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }
            
            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
            
            list1=new ArrayList<String>();
            list2=new ArrayList<String>();
            
            // 4,4
            for(int i=0;i<n-3;i++)
            {
                for(int j=i+1;j<n-2;j++)
                {
                    for(int k=j+1;k<n-1;k++)
                    {
                        for(int m=k+1;m<n;m++)
                        {
                            temp1.append(y.charAt(i));
                            temp1.append(y.charAt(j));
                            temp1.append(y.charAt(k));
                            temp1.append(y.charAt(m));
                            str1=temp1.toString();
                            
                            temp2.append(y.substring(0,i));
                            temp2.append(y.substring(i+1,j));
                            temp2.append(y.substring(j+1,k));
                            temp2.append(y.substring(k+1,m));
                            temp2.append(y.substring(m+1,n));
                            str2=temp2.toString();

                            list1.add(str1);
                            list2.add(str2);
                            
                            temp1=new StringBuilder();
                            temp2=new StringBuilder();
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),false);
        }
        else if(n==9)
        {
            // 3,6
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);

            // 3,3,3
            n=6;
            int q;
            for(int l=0;l<list2.size();l++)
            {
                for(int i=0;i<n-2;i++)
                {
                    for(int j=i+1;j<n-1;j++)
                    {
                        for(int k=j+1;k<n;k++)
                        {
                            temp1.append(list2.get(l).charAt(i));
                            temp1.append(list2.get(l).charAt(j));
                            temp1.append(list2.get(l).charAt(k));
                            str1=temp1.toString();
                            
                            temp2.append(list2.get(l).substring(0,i));
                            temp2.append(list2.get(l).substring(i+1,j));
                            temp2.append(list2.get(l).substring(j+1,k));
                            temp2.append(list2.get(l).substring(k+1,n));
                            str2=temp2.toString();

                            DoubleSplitReturn(str1,str2,false,list3,list4);
                            q=SearchAnagrams(list1.get(l));

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            if(q!=-1)
                            {
                                ArrayList<String> t=array[q];
                                for(int m=0;m<list3.size();m++)
                                {
                                    for(int p=0;p<t.size();p++)
                                    {
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));
        
                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();
                                    }
                                }
                            }

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            list3=new ArrayList<String>();
                            list4=new ArrayList<String>();
                            k=Shift(list2.get(l),k);
                        }
                        j=Shift(list2.get(l),j);
                    }
                    i=Shift(list2.get(l),i);
                }
            }
            
            list1=new ArrayList<String>();
            list2=new ArrayList<String>();

            // 4,5
            n=9;
            for(int i=0;i<n-3;i++)
            {
                for(int j=i+1;j<n-2;j++)
                {
                    for(int k=j+1;k<n-1;k++)
                    {
                        for(int m=k+1;m<n;m++)
                        {
                            temp1.append(y.charAt(i));
                            temp1.append(y.charAt(j));
                            temp1.append(y.charAt(k));
                            temp1.append(y.charAt(m));
                            str1=temp1.toString();
                            
                            temp2.append(y.substring(0,i));
                            temp2.append(y.substring(i+1,j));
                            temp2.append(y.substring(j+1,k));
                            temp2.append(y.substring(k+1,m));
                            temp2.append(y.substring(m+1,n));
                            str2=temp2.toString();
    
                            list1.add(str1);
                            list2.add(str2);
                            
                            temp1=new StringBuilder();
                            temp2=new StringBuilder();
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
        }
        else if(n==10)
        {
            // 3,7
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
            
            list1=new ArrayList<String>();
            list2=new ArrayList<String>();
            
            // 4,6
            for(int i=0;i<n-3;i++)
            {
                for(int j=i+1;j<n-2;j++)
                {
                    for(int k=j+1;k<n-1;k++)
                    {
                        for(int m=k+1;m<n;m++)
                        {
                            temp1.append(y.charAt(i));
                            temp1.append(y.charAt(j));
                            temp1.append(y.charAt(k));
                            temp1.append(y.charAt(m));
                            str1=temp1.toString();

                            temp2.append(y.substring(0,i));
                            temp2.append(y.substring(i+1,j));
                            temp2.append(y.substring(j+1,k));
                            temp2.append(y.substring(k+1,m));
                            temp2.append(y.substring(m+1,n));
                            str2=temp2.toString();

                            list1.add(str1);
                            list2.add(str2);
                            
                            temp1=new StringBuilder();
                            temp2=new StringBuilder();
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
            
            // 4,3,3
            n=6;
            int q;
            for(int l=0;l<list2.size();l++)
            {
                for(int i=0;i<n-2;i++)
                {
                    for(int j=i+1;j<n-1;j++)
                    {
                        for(int k=j+1;k<n;k++)
                        {
                            temp1.append(list2.get(l).charAt(i));
                            temp1.append(list2.get(l).charAt(j));
                            temp1.append(list2.get(l).charAt(k));
                            str1=temp1.toString();
                            
                            temp2.append(list2.get(l).substring(0,i));
                            temp2.append(list2.get(l).substring(i+1,j));
                            temp2.append(list2.get(l).substring(j+1,k));
                            temp2.append(list2.get(l).substring(k+1,n));
                            str2=temp2.toString();

                            DoubleSplitReturn(str1, str2, false, list3, list4);
                            q=SearchAnagrams(list1.get(l));

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            if(q!=-1)
                            {
                                ArrayList<String> t=array[q];
                                for(int m=0;m<list3.size();m++)
                                {
                                    for(int p=0;p<t.size();p++)
                                    {
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));

                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();

                                        temp2.append(list3.get(m));
                                        temp2.append(" ");
                                        temp2.append(list4.get(m));
                                        temp2.append(" ");
                                        temp2.append(t.get(p));

                                        FINAL.add(temp2.toString());
                                        temp2=new StringBuilder();

                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));
                                        
                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();
                                    }
                                }
                            }

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            list3=new ArrayList<String>();
                            list4=new ArrayList<String>();
                            k=Shift(list2.get(l),k);
                        }
                        j=Shift(list2.get(l),j);
                    }
                    i=Shift(list2.get(l),i);
                }
            }

            list1=new ArrayList<String>();
            list2=new ArrayList<String>();
            
            // 5,5
            n=10;
            for(int l=0;l<n-4;l++)
            {
                for(int i=l+1;i<n-3;i++)
                {
                    for(int j=i+1;j<n-2;j++)
                    {
                        for(int k=j+1;k<n-1;k++)
                        {
                            for(int m=k+1;m<n;m++)
                            {
                                temp1.append(y.charAt(l));
                                temp1.append(y.charAt(i));
                                temp1.append(y.charAt(j));
                                temp1.append(y.charAt(k));
                                temp1.append(y.charAt(m));
                                str1=temp1.toString();
                                
                                temp2.append(y.substring(0,l));
                                temp2.append(y.substring(l+1,i));
                                temp2.append(y.substring(i+1,j));
                                temp2.append(y.substring(j+1,k));
                                temp2.append(y.substring(k+1,m));
                                temp2.append(y.substring(m+1,n));
                                str2=temp2.toString();

                                list1.add(str1);
                                list2.add(str2);
                                
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
                                m=Shift(y,m);
                            }
                            k=Shift(y,k);
                        }
                        j=Shift(y,j);
                    }
                    i=Shift(y,i);
                }
                l=Shift(y,l);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),false);
        }
        else if(n==11)
        {
            // 3,8
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
            
            // 3,4,4
            n=8;
            int q;
            for(int l=0;l<list2.size();l++)
            {
                for(int r=0;r<n-3;r++)
                {
                    for(int i=r+1;i<n-2;i++)
                    {
                        for(int j=i+1;j<n-1;j++)
                        {
                            for(int k=j+1;k<n;k++)
                            {
                                temp1.append(list2.get(l).charAt(r));
                                temp1.append(list2.get(l).charAt(i));
                                temp1.append(list2.get(l).charAt(j));
                                temp1.append(list2.get(l).charAt(k));
                                str1=temp1.toString();
                                
                                temp2.append(list2.get(l).substring(0,r));
                                temp2.append(list2.get(l).substring(r+1,i));
                                temp2.append(list2.get(l).substring(i+1,j));
                                temp2.append(list2.get(l).substring(j+1,k));
                                temp2.append(list2.get(l).substring(k+1,n));
                                str2=temp2.toString();
    
                                DoubleSplitReturn(str1, str2, false, list3, list4);
                                q=SearchAnagrams(list1.get(l));
    
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
    
                                if(q!=-1)
                                {
                                    ArrayList<String> t=array[q];
                                    for(int m=0;m<list3.size();m++)
                                    {
                                        for(int p=0;p<t.size();p++)
                                        {
                                            temp1.append(t.get(p));
                                            temp1.append(" ");
                                            temp1.append(list3.get(m));
                                            temp1.append(" ");
                                            temp1.append(list4.get(m));
    
                                            FINAL.add(temp1.toString());
                                            temp1=new StringBuilder();
    
                                            temp2.append(list3.get(m));
                                            temp2.append(" ");
                                            temp2.append(list4.get(m));
                                            temp2.append(" ");
                                            temp2.append(t.get(p));
    
                                            FINAL.add(temp2.toString());
                                            temp2=new StringBuilder();
    
                                            temp1.append(list3.get(m));
                                            temp1.append(" ");
                                            temp1.append(t.get(p));
                                            temp1.append(" ");
                                            temp1.append(list4.get(m));
                                            
                                            FINAL.add(temp1.toString());
                                            temp1=new StringBuilder();
                                        }
                                    }
                                }
    
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
    
                                list3=new ArrayList<String>();
                                list4=new ArrayList<String>();
                                k=Shift(list2.get(l),k);
                            }
                            j=Shift(list2.get(l),j);
                        }
                        i=Shift(list2.get(l),i);
                    }
                    r=Shift(list2.get(l),r);
                }
            }

            list1=new ArrayList<String>();
            list2=new ArrayList<String>();

            // 4,7
            n=11;
            for(int i=0;i<n-3;i++)
            {
                for(int j=i+1;j<n-2;j++)
                {
                    for(int k=j+1;k<n-1;k++)
                    {
                        for(int m=k+1;m<n;m++)
                        {
                            temp1.append(y.charAt(i));
                            temp1.append(y.charAt(j));
                            temp1.append(y.charAt(k));
                            temp1.append(y.charAt(m));
                            str1=temp1.toString();

                            temp2.append(y.substring(0,i));
                            temp2.append(y.substring(i+1,j));
                            temp2.append(y.substring(j+1,k));
                            temp2.append(y.substring(k+1,m));
                            temp2.append(y.substring(m+1,n));
                            str2=temp2.toString();

                            list1.add(str1);
                            list2.add(str2);
                            
                            temp1=new StringBuilder();
                            temp2=new StringBuilder();
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }
            
            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);

            list1=new ArrayList<String>();
            list2=new ArrayList<String>();

            // 5,6
            for(int i=0;i<n-4;i++)
            {
                for(int j=i+1;j<n-3;j++)
                {
                    for(int k=j+1;k<n-2;k++)
                    {
                        for(int m=k+1;m<n-1;m++)
                        {
                            for(int r=m+1;r<n;r++)
                            {
                                temp1.append(y.charAt(i));
                                temp1.append(y.charAt(j));
                                temp1.append(y.charAt(k));
                                temp1.append(y.charAt(m));
                                temp1.append(y.charAt(r));
                                str1=temp1.toString();
    
                                temp2.append(y.substring(0,i));
                                temp2.append(y.substring(i+1,j));
                                temp2.append(y.substring(j+1,k));
                                temp2.append(y.substring(k+1,m));
                                temp2.append(y.substring(m+1,r));
                                temp2.append(y.substring(r+1,n));
                                str2=temp2.toString();
    
                                list1.add(str1);
                                list2.add(str2);
                                
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
                                r=Shift(y,r);
                            }
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);
            
            // 5,3,3
            n=6;
            for(int l=0;l<list2.size();l++)
            {
                for(int i=0;i<n-2;i++)
                {
                    for(int j=i+1;j<n-1;j++)
                    {
                        for(int k=j+1;k<n;k++)
                        {
                            temp1.append(list2.get(l).charAt(i));
                            temp1.append(list2.get(l).charAt(j));
                            temp1.append(list2.get(l).charAt(k));
                            str1=temp1.toString();
                            
                            temp2.append(list2.get(l).substring(0,i));
                            temp2.append(list2.get(l).substring(i+1,j));
                            temp2.append(list2.get(l).substring(j+1,k));
                            temp2.append(list2.get(l).substring(k+1,n));
                            str2=temp2.toString();

                            DoubleSplitReturn(str1, str2, false, list3, list4);
                            q=SearchAnagrams(list1.get(l));

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            if(q!=-1)
                            {
                                ArrayList<String> t=array[q];
                                for(int m=0;m<list3.size();m++)
                                {
                                    for(int p=0;p<t.size();p++)
                                    {
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));

                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();

                                        temp2.append(list3.get(m));
                                        temp2.append(" ");
                                        temp2.append(list4.get(m));
                                        temp2.append(" ");
                                        temp2.append(t.get(p));

                                        FINAL.add(temp2.toString());
                                        temp2=new StringBuilder();

                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));
                                        
                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();
                                    }
                                }
                            }

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            list3=new ArrayList<String>();
                            list4=new ArrayList<String>();
                            k=Shift(list2.get(l),k);
                        }
                        j=Shift(list2.get(l),j);
                    }
                    i=Shift(list2.get(l),i);
                }
            }
        }
        else if(n==12)
        {
            // 3,9
            for(int i=0;i<n-2;i++)
            {
                for(int j=i+1;j<n-1;j++)
                {
                    for(int k=j+1;k<n;k++)
                    {
                        temp1.append(y.charAt(i));
                        temp1.append(y.charAt(j));
                        temp1.append(y.charAt(k));
                        str1=temp1.toString();
                        
                        temp2.append(y.substring(0,i));
                        temp2.append(y.substring(i+1,j));
                        temp2.append(y.substring(j+1,k));
                        temp2.append(y.substring(k+1,n));
                        str2=temp2.toString();

                        list1.add(str1);
                        list2.add(str2);
                        
                        temp1=new StringBuilder();
                        temp2=new StringBuilder();
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);

            // 3,4,5
            int q;
            n=9;
            for(int l=0;l<list2.size();l++)
            {
                for(int r=0;r<n-3;r++)
                {
                    for(int i=r+1;i<n-2;i++)
                    {
                        for(int j=i+1;j<n-1;j++)
                        {
                            for(int k=j+1;k<n;k++)
                            {
                                temp1.append(list2.get(l).charAt(r));
                                temp1.append(list2.get(l).charAt(i));
                                temp1.append(list2.get(l).charAt(j));
                                temp1.append(list2.get(l).charAt(k));
                                str1=temp1.toString();
                                
                                temp2.append(list2.get(l).substring(0,r));
                                temp2.append(list2.get(l).substring(r+1,i));
                                temp2.append(list2.get(l).substring(i+1,j));
                                temp2.append(list2.get(l).substring(j+1,k));
                                temp2.append(list2.get(l).substring(k+1,n));
                                str2=temp2.toString();

                                DoubleSplitReturn(str1, str2, true, list3, list4);
                                q=SearchAnagrams(list1.get(l));
    
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
    
                                if(q!=-1)
                                {
                                    ArrayList<String> t=array[q];
                                    for(int m=0;m<list3.size();m++)
                                    {
                                        for(int p=0;p<t.size();p++)
                                        {
                                            temp1.append(t.get(p));
                                            temp1.append(" ");
                                            temp1.append(list3.get(m));
                                            temp1.append(" ");
                                            temp1.append(list4.get(m));
    
                                            FINAL.add(temp1.toString());
                                            temp1=new StringBuilder();
    
                                            temp2.append(list3.get(m));
                                            temp2.append(" ");
                                            temp2.append(list4.get(m));
                                            temp2.append(" ");
                                            temp2.append(t.get(p));
    
                                            FINAL.add(temp2.toString());
                                            temp2=new StringBuilder();
    
                                            temp1.append(list3.get(m));
                                            temp1.append(" ");
                                            temp1.append(t.get(p));
                                            temp1.append(" ");
                                            temp1.append(list4.get(m));
                                            
                                            FINAL.add(temp1.toString());
                                            temp1=new StringBuilder();
                                        }
                                    }
                                }
    
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
    
                                list3=new ArrayList<String>();
                                list4=new ArrayList<String>();
                                k=Shift(list2.get(l),k);
                            }
                            j=Shift(list2.get(l),j);
                        }
                        i=Shift(list2.get(l),i);
                    }
                    r=Shift(list2.get(l),r);
                }
            }

            list1=new ArrayList<String>();
            list2=new ArrayList<String>();
            
            // 4,8
            n=12;
            for(int i=0;i<n-3;i++)
            {
                for(int j=i+1;j<n-2;j++)
                {
                    for(int k=j+1;k<n-1;k++)
                    {
                        for(int m=k+1;m<n;m++)
                        {
                            temp1.append(y.charAt(i));
                            temp1.append(y.charAt(j));
                            temp1.append(y.charAt(k));
                            temp1.append(y.charAt(m));
                            str1=temp1.toString();

                            temp2.append(y.substring(0,i));
                            temp2.append(y.substring(i+1,j));
                            temp2.append(y.substring(j+1,k));
                            temp2.append(y.substring(k+1,m));
                            temp2.append(y.substring(m+1,n));
                            str2=temp2.toString();

                            list1.add(str1);
                            list2.add(str2);
                            
                            temp1=new StringBuilder();
                            temp2=new StringBuilder();
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }
            
            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);

            // 4,4,4
            n=8;
            for(int l=0;l<list2.size();l++)
            {
                for(int r=0;r<n-3;r++)
                {
                    for(int i=r+1;i<n-2;i++)
                    {
                        for(int j=i+1;j<n-1;j++)
                        {
                            for(int k=j+1;k<n;k++)
                            {
                                temp1.append(list2.get(l).charAt(r));
                                temp1.append(list2.get(l).charAt(i));
                                temp1.append(list2.get(l).charAt(j));
                                temp1.append(list2.get(l).charAt(k));
                                str1=temp1.toString();
                                
                                temp2.append(list2.get(l).substring(0,r));
                                temp2.append(list2.get(l).substring(r+1,i));
                                temp2.append(list2.get(l).substring(i+1,j));
                                temp2.append(list2.get(l).substring(j+1,k));
                                temp2.append(list2.get(l).substring(k+1,n));
                                str2=temp2.toString();
    
                                DoubleSplitReturn(str1,str2,false,list3,list4);
                                q=SearchAnagrams(list1.get(l));
    
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
    
                                if(q!=-1)
                                {
                                    ArrayList<String> t=array[q];
                                    for(int m=0;m<list3.size();m++)
                                    {
                                        for(int p=0;p<t.size();p++)
                                        {
                                            temp1.append(t.get(p));
                                            temp1.append(" ");
                                            temp1.append(list3.get(m));
                                            temp1.append(" ");
                                            temp1.append(list4.get(m));
            
                                            FINAL.add(temp1.toString());
                                            temp1=new StringBuilder();
                                        }
                                    }
                                }
    
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
    
                                list3=new ArrayList<String>();
                                list4=new ArrayList<String>();
                                k=Shift(list2.get(l),k);
                            }
                            j=Shift(list2.get(l),j);
                        }
                        i=Shift(list2.get(l),i);
                    }
                    r=Shift(list2.get(l),r);
                }
            }

            list1=new ArrayList<String>();
            list2=new ArrayList<String>();
            
            // 5,7
            n=12;
            for(int i=0;i<n-4;i++)
            {
                for(int j=i+1;j<n-3;j++)
                {
                    for(int k=j+1;k<n-2;k++)
                    {
                        for(int m=k+1;m<n-1;m++)
                        {
                            for(int r=m+1;r<n;r++)
                            {
                                temp1.append(y.charAt(i));
                                temp1.append(y.charAt(j));
                                temp1.append(y.charAt(k));
                                temp1.append(y.charAt(m));
                                temp1.append(y.charAt(r));
                                str1=temp1.toString();
    
                                temp2.append(y.substring(0,i));
                                temp2.append(y.substring(i+1,j));
                                temp2.append(y.substring(j+1,k));
                                temp2.append(y.substring(k+1,m));
                                temp2.append(y.substring(m+1,r));
                                temp2.append(y.substring(r+1,n));
                                str2=temp2.toString();
    
                                list1.add(str1);
                                list2.add(str2);
                                
                                temp1=new StringBuilder();
                                temp2=new StringBuilder();
                                r=Shift(y,r);
                            }
                            m=Shift(y,m);
                        }
                        k=Shift(y,k);
                    }
                    j=Shift(y,j);
                }
                i=Shift(y,i);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),true);

            list1=new ArrayList<String>();
            list2=new ArrayList<String>();

            // 6,6
            for(int r=0;r<n-5;r++)
            {
                for(int l=r+1;l<n-4;l++)
                {
                    for(int i=l+1;i<n-3;i++)
                    {
                        for(int j=i+1;j<n-2;j++)
                        {
                            for(int k=j+1;k<n-1;k++)
                            {
                                for(int m=k+1;m<n;m++)
                                {
                                    temp1.append(y.charAt(r));
                                    temp1.append(y.charAt(l));
                                    temp1.append(y.charAt(i));
                                    temp1.append(y.charAt(j));
                                    temp1.append(y.charAt(k));
                                    temp1.append(y.charAt(m));
                                    str1=temp1.toString();
                                    
                                    temp2.append(y.substring(0,r));
                                    temp2.append(y.substring(r+1,l));
                                    temp2.append(y.substring(l+1,i));
                                    temp2.append(y.substring(i+1,j));
                                    temp2.append(y.substring(j+1,k));
                                    temp2.append(y.substring(k+1,m));
                                    temp2.append(y.substring(m+1,n));
                                    str2=temp2.toString();
    
                                    list1.add(str1);
                                    list2.add(str2);
                                    
                                    temp1=new StringBuilder();
                                    temp2=new StringBuilder();
                                    m=Shift(y,m);
                                }
                                k=Shift(y,k);
                            }
                            j=Shift(y,j);
                        }
                        i=Shift(y,i);
                    }
                    l=Shift(y,l);
                }
                r=Shift(y,r);
            }

            for(int i=0;i<list1.size();i++)
                DoubleAdd(list1.get(i),list2.get(i),false);
            
            // 6,3,3
            n=6;
            for(int l=0;l<list2.size();l++)
            {
                for(int i=0;i<n-2;i++)
                {
                    for(int j=i+1;j<n-1;j++)
                    {
                        for(int k=j+1;k<n;k++)
                        {
                            temp1.append(list2.get(l).charAt(i));
                            temp1.append(list2.get(l).charAt(j));
                            temp1.append(list2.get(l).charAt(k));
                            str1=temp1.toString();
                            
                            temp2.append(list2.get(l).substring(0,i));
                            temp2.append(list2.get(l).substring(i+1,j));
                            temp2.append(list2.get(l).substring(j+1,k));
                            temp2.append(list2.get(l).substring(k+1,n));
                            str2=temp2.toString();

                            DoubleSplitReturn(str1, str2, false, list3, list4);
                            q=SearchAnagrams(list1.get(l));

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            if(q!=-1)
                            {
                                ArrayList<String> t=array[q];
                                for(int m=0;m<list3.size();m++)
                                {
                                    for(int p=0;p<t.size();p++)
                                    {
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));

                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();

                                        temp2.append(list3.get(m));
                                        temp2.append(" ");
                                        temp2.append(list4.get(m));
                                        temp2.append(" ");
                                        temp2.append(t.get(p));

                                        FINAL.add(temp2.toString());
                                        temp2=new StringBuilder();

                                        temp1.append(list3.get(m));
                                        temp1.append(" ");
                                        temp1.append(t.get(p));
                                        temp1.append(" ");
                                        temp1.append(list4.get(m));
                                        
                                        FINAL.add(temp1.toString());
                                        temp1=new StringBuilder();
                                    }
                                }
                            }

                            temp1=new StringBuilder();
                            temp2=new StringBuilder();

                            list3=new ArrayList<String>();
                            list4=new ArrayList<String>();
                            k=Shift(list2.get(l),k);
                        }
                        j=Shift(list2.get(l),j);
                    }
                    i=Shift(list2.get(l),i);
                }
            }
        }
    }

    public void DoubleAdd(String x,String y,boolean repeat)
    {
        int n1=SearchAnagrams(x);
        StringBuilder temp=new StringBuilder();
        if(n1!=-1)
        {            
            int n2=SearchAnagrams(y);
            if(n2!=-1)
            {
                ArrayList<String> X=array[n1];
                ArrayList<String> Y=array[n2];

                for(int i=0;i<X.size();i++)
                {
                    for(int j=0;j<Y.size();j++)
                    {
                        temp.append(X.get(i));
                        temp.append(" ");
                        temp.append(Y.get(j));

                        FINAL.add(temp.toString());
                        temp=new StringBuilder();

                        if(repeat)
                        {
                            temp.append(Y.get(j));
                            temp.append(" ");
                            temp.append(X.get(i));
                            
                            FINAL.add(temp.toString());
                            temp=new StringBuilder();
                        }
                    }
                }
            }
        }
    }

    public void DoubleSplitReturn(String x,String y,boolean repeat,ArrayList<String> list1,ArrayList<String> list2)
    {
        int n1=SearchAnagrams(x);
        if(n1!=-1)
        {            
            int n2=SearchAnagrams(y);
            if(n2!=-1)
            {
                ArrayList<String> X=array[n1];
                ArrayList<String> Y=array[n2];

                for(int i=0;i<X.size();i++)
                {
                    for(int j=0;j<Y.size();j++)
                    {
                        list1.add(X.get(i));
                        list2.add(Y.get(j));

                        if(repeat)
                        {
                            list1.add(Y.get(j));
                            list2.add(X.get(i));
                        }
                    }
                }
            }
        }
    }

    public int Shift(String y,int i)
    {
        int j=i;
        while(j<y.length() && y.substring(j,j+1).equals(y.substring(i,i+1)))
            j++;
        j--;
        return j--;
    }

    public ArrayList<String> DoubleReturn(String x,String y,boolean repeat)
    {
        int n1=SearchAnagrams(x);
        ArrayList<String> result=new ArrayList<String>();
        StringBuilder temp=new StringBuilder();
        if(n1!=-1)
        {            
            int n2=SearchAnagrams(y);
            if(n2!=-1)
            {
                ArrayList<String> X=array[n1];
                ArrayList<String> Y=array[n2];

                for(int i=0;i<X.size();i++)
                {
                    for(int j=0;j<Y.size();j++)
                    {
                        temp.append(X.get(i));
                        temp.append(" ");
                        temp.append(Y.get(j));

                        result.add(temp.toString());
                        temp=new StringBuilder();

                        if(repeat)
                        {
                            temp.append(Y.get(j));
                            temp.append(" ");
                            temp.append(X.get(i));
                            
                            result.add(temp.toString());
                            temp=new StringBuilder();
                        }
                    }
                }
            }
        }

        return result;
    }

    public String SortedString(String x)
    {
        char[] list=x.toCharArray();
        Arrays.sort(list);
        return new String(list);
    }
    
    public int ComputeIndex(char x)
    {
        if(x=='\'')
            return 36;
        else if(97<=x && x<=122)
            return x-97;
        else
            return x-22;
    }
    
    public Word Hasher1(String x)
    {
        int n=x.length();
        int r;
        int hash=1;
        char[] list=x.toCharArray();
        Arrays.sort(list);

        for(int i=0;i<n;i++)
        {
            r=ComputeIndex(list[i]);
            hash=(hash*primes[r])%SIZE;
        }

        return new Word(new String(list),hash);
    }

    public int Hasher2(String x)
    {
        int hash=0;
        int n=x.length();
        for(int i=0;i<n;i++)
            hash+=x.charAt(i);
        return hash;
    }

    public ArrayList<String> FirstOrderAnagrams(String x)
    {
        int index=SearchAnagrams(x);
        ArrayList<String> y=new ArrayList<String>();
        
        if(index>=0)
            return array[index];
        
        return new ArrayList<String>();
    }
}

class Word
{
    String SortedWord;
    int Hash1;

    public Word(String x,int y)
    {
        SortedWord=x;
        Hash1=y;
    }
}