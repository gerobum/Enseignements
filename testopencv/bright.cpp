#include <opencv2/opencv.hpp>
#include <iostream>
#include <algorithm>
using namespace cv;

int main(int argc, char** argv) {
    double alpha; /*< Simple contrast control */
    int beta; /*< Simple brightness control */
    Mat im0 = imread(argv[1]);
    Mat new_image = Mat::zeros(im0.size(), im0.type());
    Mat im1 = Mat::zeros(im0.size(), im0.type());
    Mat im2 = Mat::zeros(im0.size(), im0.type());
    Mat im3 = Mat::zeros(im0.size(), im0.type());
    Mat im4 = Mat::zeros(im0.size(), im0.type());
    Mat im5 = Mat::zeros(im0.size(), im0.type());
    std::cout << " Basic Linear Transforms " << std::endl;
    std::cout << "-------------------------" << std::endl;
    std::cout << "* Enter the alpha value [1.0-3.0]: ";
    std::cin>>alpha;
    std::cout << "* Enter the beta value [0-100]: ";
    std::cin>>beta;
    for (int y = 0; y < im0.rows; y++) {
        for (int x = 0; x < im0.cols; x++) {
            for (int c = 0; c < 3; c++) {
                new_image.at<Vec3b>(y, x)[c] =
                        saturate_cast<uchar>(alpha * (im0.at<Vec3b>(y, x)[c]) + beta);
                Vec3b v = im0.at<Vec3b>(y, x);
                swap(v[1], v[2]);
                im1.at<Vec3b>(y, x) = v;
                swap(v[0], v[1]);
                im2.at<Vec3b>(y, x) = v;
                swap(v[0], v[2]);
                im3.at<Vec3b>(y, x) = v;
                swap(v[1], v[2]);
                im4.at<Vec3b>(y, x) = v;
                swap(v[0], v[1]);
                im5.at<Vec3b>(y, x) = v;
            }
        }
    }
    namedWindow("Original Image", 1);
    namedWindow("New Image", 1);
    namedWindow("1", 1);
    namedWindow("2", 1);
    namedWindow("3", 1);
    namedWindow("4", 1);
    namedWindow("5", 1);
    imshow("Original Image", im0);
    imshow("New Image", new_image);
    imshow("1", im1);
    imshow("2", im2);
    imshow("3", im3);
    imshow("4", im4);
    imshow("5", im5);
    waitKey();
    return 0;
}