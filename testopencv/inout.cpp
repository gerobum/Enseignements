#include <opencv2/imgcodecs.hpp>
#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include "opencv2/core/core.hpp"
#include <iostream>

using namespace std;
using namespace cv;

int main(int argc, char* argv[]) {

    Mat img = imread(argv[1], 0);
    Scalar intensity = img.at<uchar>(10, 10);


    cout << intensity << ", nb lignes = " << img.rows << ", nb colonnes = " << img.cols << endl;
    
    imshow("image", img);

    waitKey();

    Mat grey;
    cvtColor(img, grey, COLOR_BGR2GRAY);
    waitKey();
    Mat sobelx;
    Sobel(grey, sobelx, CV_32F, 1, 0);
    double minVal, maxVal;
    minMaxLoc(sobelx, &minVal, &maxVal); //find minimum and maximum intensities
    
    cout << "sobel " << " mini : " << minVal << ", maxi : " << maxVal << endl;
    
    waitKey();
    Mat draw;
    sobelx.convertTo(draw, CV_8U, 255.0 / (maxVal - minVal), -minVal * 255.0 / (maxVal - minVal));
    namedWindow("image", WINDOW_AUTOSIZE);
    imshow("image", draw);
    waitKey();


    return 0;
}
