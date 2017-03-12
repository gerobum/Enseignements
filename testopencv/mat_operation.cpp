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

        cout << img;
}
