// BezierCurveModeller.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

#include <GL/glew.h>
#include <GL/freeglut.h>
#include <GL/glext.h>

#pragma comment(lib, "glew32.lib")

#include <iostream>
#include <cmath>
#include <string>
#include <fstream>
#include <sstream>
#include <algorithm>
#include <cassert>
using namespace std;

int NUM_POINTS(0), POINT_DIMS(3);
float MAX_X(0), MAX_Y(0);

float *CONTROL_POINTS_ARRAY = 0;

void printControlPoints() 
{
	if (CONTROL_POINTS_ARRAY != 0) {
		for (int i = 0; i < NUM_POINTS; ++i) {
				cout << CONTROL_POINTS_ARRAY[i*POINT_DIMS] << ' '
					 << CONTROL_POINTS_ARRAY[i*POINT_DIMS+1] << ' '
					 << CONTROL_POINTS_ARRAY[i*POINT_DIMS+2];
			cout << endl;
		}
	}
	else {
		cout << "The control points array is empty!" << endl;
	}
}

void compute2DBoundingBox() {
	assert(CONTROL_POINTS_ARRAY != 0);
	for (int i = 0; i < NUM_POINTS; ++i) {
		if (fabs(CONTROL_POINTS_ARRAY[i*POINT_DIMS]) > MAX_X)
			MAX_X = fabs(CONTROL_POINTS_ARRAY[i*POINT_DIMS]);

		if (fabs(CONTROL_POINTS_ARRAY[i*POINT_DIMS+1]) > MAX_Y)
			MAX_Y = fabs(CONTROL_POINTS_ARRAY[i*POINT_DIMS + 1]);
	}
	cout << "Max x: " << MAX_X << ", " << "Max y: " << MAX_Y << endl;
}

bool loadControlPoints(const string& fname, float*& controlPointsData, int& numPoints, int& pointDims)
{
	ifstream inputs(fname);
	if (inputs) {
		string line;
		
		// Read the file header
		getline(inputs, line, '\n');
		if (!line.empty()) {
			istringstream iss(line);
			iss >> numPoints >> pointDims;
			cout << "Num of points: " << numPoints << ", point dims: " << pointDims << endl;

			// Allocate the memory for the control points array
			controlPointsData = new float[numPoints*pointDims];
			assert(controlPointsData != 0);
			for (int i = 0; i < numPoints*pointDims; ++i) {
				controlPointsData[i] = 0.f;
			}
		}
		else {
			cout << "Missing the file head, wrong file format!" << endl;
			return false;
		}
		
		///////////////////////// TO DO //////////////////////////////////
		// 按行读取控制顶点的坐标，将读取的坐标点存入数组controlPointsData
		
		
		
		///////////////////////////////////////////////////////////////////
		
		return true;
	}
	else {
		return false;
	}
}


void init()
{
	glClearColor(1.f, 1.f, 1.f, 0.f);
	
	assert(CONTROL_POINTS_ARRAY != 0);
	////////////////////////////////// TO DO //////////////////////////////
	// 设置并开启Bezier曲线求值器
	
	///////////////////////////////////////////////////////////////////////	
}

void display()
{
	glClear(GL_COLOR_BUFFER_BIT);
	glColor3f(1.f, 0.f, 0.f);
	
	// Draw the control points
	glPointSize(5.f);
	glBegin(GL_POINTS);
	for (int i = 0; i < NUM_POINTS; ++i) {
		glVertex3f(	CONTROL_POINTS_ARRAY[i*POINT_DIMS], 
					CONTROL_POINTS_ARRAY[i*POINT_DIMS+1],
					CONTROL_POINTS_ARRAY[i*POINT_DIMS+2]);
	}
	glEnd();

	// Draw the control polygon
	glColor3f(0.f, 1.f, 0.f);
	glBegin(GL_LINE_STRIP);
	for (int i = 0; i < NUM_POINTS; ++i) {
		glVertex3f(CONTROL_POINTS_ARRAY[i*POINT_DIMS],
			CONTROL_POINTS_ARRAY[i*POINT_DIMS + 1],
			CONTROL_POINTS_ARRAY[i*POINT_DIMS + 2]);
	}
	glEnd();

	glColor3f(0.f, 0.f, 1.f);
	glLineWidth(2.f);
	/////////////////////////// TO DO ///////////////////////////////
	// 绘制Bezier曲线
	
	
	/////////////////////////////////////////////////////////////////

	glutSwapBuffers();
}

void reshape(int w, int h)
{
	glViewport(0, 0, w, h);

	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluOrtho2D(-MAX_X*1.5f, MAX_X*1.5f, -MAX_Y*1.5f, MAX_Y*1.5f);
}

int main(int argc, char** argv)
{
	if (argc < 2) {
		cout << "Usage: " << argv[0] << " control_points_filename" << endl;
		return 1;
	}
	else {
		if (loadControlPoints(argv[1], CONTROL_POINTS_ARRAY, NUM_POINTS, POINT_DIMS)) {
			cout << "The file is load successfully!" << endl;
			printControlPoints();
			compute2DBoundingBox();
		}
		else {
			cout << "Failed load the file" << endl;
			return 0;
		}

		glutInit(&argc, argv);

		glutInitContextVersion(2, 1);
		glutInitContextProfile(GLUT_COMPATIBILITY_PROFILE);

		glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);

		glutInitWindowPosition(100, 100);
		glutInitWindowSize(600, 600);
		glutCreateWindow("BezierCurveModeller");

		glutDisplayFunc(display);
		glutReshapeFunc(reshape);

		glewExperimental = GL_TRUE;
		glewInit();

		init();

		glutMainLoop();
		
		// Free the memory
		if (CONTROL_POINTS_ARRAY != 0) {
			delete[] CONTROL_POINTS_ARRAY;
		}
		return 0;
	}
}

