/**
 * @file Drawing_2.cpp
 * @brief Simple sample code
 */

#include <opencv2/core.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/highgui.hpp>
#include <iostream>
#include <stdio.h>

using namespace cv;
using namespace std;

/// Global Variables
const int DELAY = 25;

const int window_width = 900;
const int window_height = 600;
int x_1 = -window_width/2;
int x_2 = window_width*3/2;
int y_1 = -window_width/2;
int y_2 = window_width*3/2;

/// Function headers
static Scalar randomColor( RNG& rng );
int Drawing_Random_Lines( Mat image, char* window_name, RNG rng );
int Drawing_Random_Rectangles( Mat image, char* window_name, RNG rng );
int Drawing_Random_Ellipses( Mat image, char* window_name, RNG rng );
int Drawing_Random_Polylines( Mat image, char* window_name, RNG rng );
int Drawing_Random_Filled_Polygons( Mat image, char* window_name, RNG rng );
int Drawing_Random_Circles( Mat image, char* window_name, RNG rng );
int Displaying_Random_Text( Mat image, char* window_name, RNG rng );
int Displaying_Big_End( Mat image, char* window_name, RNG rng );


/**
 * @function main
 */
int main( void )
{
  int c = 0;

  /// Start creating a window
  char window_name[] = "Drawing_2 Tutorial";

  /// Also create a random object (RNG)
  //RNG rng( 0xFFFFFFFF );
  RNG rng(time(0));

  /// Initialize a matrix filled with zeros
  Mat image = Mat::zeros( window_height, window_width, CV_8UC3 );
  /// Show it in a window during DELAY ms
  imshow( window_name, image );

  /// Now, let's draw some lines
  c = Drawing_Random_Lines(image, window_name, rng);
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );
  

  /// Go on drawing, this time nice rectangles
  c = Drawing_Random_Rectangles(image, window_name, rng);
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  /// Draw some ellipses
  c = Drawing_Random_Ellipses( image, window_name, rng );
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  /// Now some polylines
  c = Drawing_Random_Polylines( image, window_name, rng );
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  /// Draw filled polygons
  c = Drawing_Random_Filled_Polygons( image, window_name, rng );
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  /// Draw circles
  c = Drawing_Random_Circles( image, window_name, rng );
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  /// Display text in random positions
  c = Displaying_Random_Text( image, window_name, rng );
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  /// Displaying the big end!
  c = Displaying_Big_End( image, window_name, rng );
  if( c != 0 ) return 0;
  waitKey();
  image = Mat::zeros( window_height, window_width, CV_8UC3 );

  //waitKey(0);
  return 0;
}

/// Function definitions

/**
 * @function randomColor
 * @brief Produces a random color given a random object
 */
static Scalar randomColor( RNG& rng )
{
  int icolor = (unsigned) rng;
  return Scalar( icolor&255, (icolor>>8)&255, (icolor>>16)&255 );
}


/**
 * @function Drawing_Random_Lines
 */
int Drawing_Random_Lines( Mat image, char* window_name, RNG rng )
{
  Point pt1, pt2;

  int nb = rng.uniform(100, 200);
  for( int i = 0; i < nb; i++ )
  {
    pt1.x = rng.uniform( x_1, x_2 );
    pt1.y = rng.uniform( y_1, y_2 );
    pt2.x = rng.uniform( x_1, x_2 );
    pt2.y = rng.uniform( y_1, y_2 );

    line( image, pt1, pt2, randomColor(rng), rng.uniform(1, 10), 8 );
    imshow( window_name, image );
    if( waitKey( DELAY ) >= 0 )
      { return -1; }
  }

  return 0;
}

/**
 * @function Drawing_Rectangles
 */
int Drawing_Random_Rectangles( Mat image, char* window_name, RNG rng )
{
  Point pt1, pt2;
  int lineType = 8;

  int nb = rng.uniform(100, 200);
  for( int i = 0; i < nb; i++ )
  {
    pt1.x = rng.uniform( x_1, x_2 );
    pt1.y = rng.uniform( y_1, y_2 );
    pt2.x = rng.uniform( x_1, x_2 );
    pt2.y = rng.uniform( y_1, y_2 );

    rectangle( image, pt1, pt2, randomColor(rng), MAX( rng.uniform( -3, 20 ), -1 ), lineType );

    imshow( window_name, image );
    if( waitKey( DELAY ) >= 0 )
      { return -1; }
  }

  return 0;
}

/**
 * @function Drawing_Random_Ellipses
 */
int Drawing_Random_Ellipses( Mat image, char* window_name, RNG rng )
{

  int nb = rng.uniform(100, 200);
  for ( int i = 0; i < nb; i++ )
  {
    Point center;
    center.x = rng.uniform(x_1, x_2);
    center.y = rng.uniform(y_1, y_2);

    Size axes;
    axes.width = rng.uniform(0, 200);
    axes.height = rng.uniform(0, 200);

    double angle = rng.uniform(0, 180);

    ellipse( image, center, axes, angle, angle - 100, angle + 200,
             randomColor(rng), rng.uniform(-1,9), 8 );

    imshow( window_name, image );

    if( waitKey(DELAY) >= 0 )
      { return -1; }
  }

  return 0;
}

/**
 * @function Drawing_Random_Polylines
 */
int Drawing_Random_Polylines( Mat image, char* window_name, RNG rng )
{
  int lineType = 8;

  int nb = rng.uniform(100, 200);
  for( int i = 0; i< nb; i++ )
  {
    Point pt[2][3];
    pt[0][0].x = rng.uniform(x_1, x_2);
    pt[0][0].y = rng.uniform(y_1, y_2);
    pt[0][1].x = rng.uniform(x_1, x_2);
    pt[0][1].y = rng.uniform(y_1, y_2);
    pt[0][2].x = rng.uniform(x_1, x_2);
    pt[0][2].y = rng.uniform(y_1, y_2);
    pt[1][0].x = rng.uniform(x_1, x_2);
    pt[1][0].y = rng.uniform(y_1, y_2);
    pt[1][1].x = rng.uniform(x_1, x_2);
    pt[1][1].y = rng.uniform(y_1, y_2);
    pt[1][2].x = rng.uniform(x_1, x_2);
    pt[1][2].y = rng.uniform(y_1, y_2);

    const Point* ppt[2] = {pt[0], pt[1]};
    int npt[] = {3, 3};

    polylines(image, ppt, npt, 2, true, randomColor(rng), rng.uniform(1,10), lineType);

    imshow( window_name, image );
    if( waitKey(DELAY) >= 0 )
      { return -1; }
  }
  return 0;
}

/**
 * @function Drawing_Random_Filled_Polygons
 */
int Drawing_Random_Filled_Polygons( Mat image, char* window_name, RNG rng )
{
  int lineType = 8;

  int nb = rng.uniform(100, 200);
  for ( int i = 0; i < nb; i++ )
  {
    Point pt[2][3];
    pt[0][0].x = rng.uniform(x_1, x_2);
    pt[0][0].y = rng.uniform(y_1, y_2);
    pt[0][1].x = rng.uniform(x_1, x_2);
    pt[0][1].y = rng.uniform(y_1, y_2);
    pt[0][2].x = rng.uniform(x_1, x_2);
    pt[0][2].y = rng.uniform(y_1, y_2);
    pt[1][0].x = rng.uniform(x_1, x_2);
    pt[1][0].y = rng.uniform(y_1, y_2);
    pt[1][1].x = rng.uniform(x_1, x_2);
    pt[1][1].y = rng.uniform(y_1, y_2);
    pt[1][2].x = rng.uniform(x_1, x_2);
    pt[1][2].y = rng.uniform(y_1, y_2);

    const Point* ppt[2] = {pt[0], pt[1]};
    int npt[] = {3, 3};

    fillPoly( image, ppt, npt, 2, randomColor(rng), lineType );

    imshow( window_name, image );
    if( waitKey(DELAY) >= 0 )
       { return -1; }
  }
  return 0;
}

/**
 * @function Drawing_Random_Circles
 */
int Drawing_Random_Circles( Mat image, char* window_name, RNG rng )
{
  int lineType = 8;

  int nb = rng.uniform(100, 200);
  for (int i = 0; i < nb; i++)
  {
    Point center;
    center.x = rng.uniform(x_1, x_2);
    center.y = rng.uniform(y_1, y_2);

    circle( image, center, rng.uniform(0, 300), randomColor(rng),
            rng.uniform(-1, 9), lineType );

    imshow( window_name, image );
    if( waitKey(DELAY) >= 0 )
      { return -1; }
  }

  return 0;
}

/**
 * @function Displaying_Random_Text
 */
int Displaying_Random_Text( Mat image, char* window_name, RNG rng )
{
  int lineType = 8;

  int nb = rng.uniform(100, 200);
  for ( int i = 1; i < nb; i++ )
  {
    Point org;
    org.x = rng.uniform(x_1, x_2);
    org.y = rng.uniform(y_1, y_2);

    putText( image, "Testing text rendering", org, rng.uniform(0,8),
             rng.uniform(0,100)*0.05+0.1, randomColor(rng), rng.uniform(1, 10), lineType);

    imshow( window_name, image );
    if( waitKey(DELAY) >= 0 )
      { return -1; }
  }

  return 0;
}

/**
 * @function Displaying_Big_End
 */
int Displaying_Big_End( Mat image, char* window_name, RNG rng)
{
  Size textsize = getTextSize("OpenCV forever!", FONT_HERSHEY_COMPLEX, 3, 5, 0);
  Point org((window_width - textsize.width)/2, (window_height - textsize.height)/2);
  int lineType = 8;

  Mat image2;

  for( int i = 255; i > 0; --i )
  {
    image2 = image - Scalar::all(i);
    putText( image2, "OpenCV forever!", org, FONT_HERSHEY_COMPLEX, 3,
             Scalar(rng.uniform(0,255), rng.uniform(0,255), rng.uniform(0,255)), 5, lineType );

    imshow( window_name, image2 );
    if( waitKey(DELAY) >= 0 )
       { return -1; }
  }

  return 0;
}
