#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include "opencv2/core.hpp"
#include <iostream>

using namespace std;
using namespace cv;


int main(int argc, char** argv)
{
        Mat img = imread(argv[1], 0);

        imwrite("flo2.png", img);
        srand((unsigned) time(0));


        Mat imgr(512, 512, CV_8UC3);
        int r = 0;
        int v = 0;
        int b = 0;
        for(int x = 0; x< 512; ++x)
            for(int y = 0; y < 512; ++y) {
                Vec3b vec(rand()%256,rand()%256,rand()%256);
                imgr.at<Vec3b>(y, x) = vec;
                r = (r+1)%256;
                if (r == 0) {
                    v = (v+1)%256;
                    if (v == 0) {
                        b = (b+1)%256;
                    }
                }
            }



        imshow("Gris", imgr);

        waitKey();
}
