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
    
    
    cout << intensity << ", nb lignes = " << img.rows  << ", nb colonnes = " << img.cols << endl;

    waitKey();
    return 0;
}
