
#include "opencv2/imgcodecs.hpp"
#include "opencv2/highgui.hpp"
#include <iostream>
using namespace cv;

int main(void) {
    double alpha = 0.5;
    double beta;
    double input;
    Mat src1, src2, dst;
    std::cout << " Simple Linear Blender " << std::endl;
    std::cout << "-----------------------" << std::endl;
    //std::cout << "* Enter alpha [0-1]: ";
    //std::cin>>input;
    // We use the alpha provided by the user if it is between 0 and 1
    if (alpha >= 0 && alpha <= 1) {
        alpha = input;
    }
    src1 = imread("P1.JPG");
    src2 = imread("P2.JPG");
    if (src1.empty()) {
        std::cout << "Error loading src1" << std::endl;
        return -1;
    }
    if (src2.empty()) {
        std::cout << "Error loading src2" << std::endl;
        return -1;
    }
    for (double alpla = 0.0; alpha <= 1.0; alpha += 0.001) {
        beta = (1.0 - alpha);
        addWeighted(src1, alpha, src2, beta, 0.0, dst);
        imshow("Linear Blend", dst);
        waitKey(2);
    }
    
    waitKey();
    
    return 0;
}
