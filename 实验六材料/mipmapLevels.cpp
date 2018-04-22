////////////////////////////////////////////////////////////////////////////////////////
// mipmapLevels.cpp
//
// This program shows a square textured with mipmaps of a different color at each level.
// The square can be moved to see the mipmaps of different levels as they are applied.
//
// Interaction:
// Press the up and down arrow keys to move the square.
////////////////////////////////////////////////////////////////////////////////////////

#include <cstdlib>
#include <iostream>
#include <fstream>

#include <GL/glew.h>
#include <GL/freeglut.h>
#include <GL/glext.h>
#pragma comment(lib, "glew32.lib") 

using namespace std;

// Globals.
static unsigned int texture[1]; // Array of texture indices.
static float d = 0.0; // Distance parameter in gluLookAt().
static unsigned char mipmapRes64[64][64][3]; // Storage for mipmap.
static unsigned char mipmapRes32[32][32][3]; // Storage for mipmap.
static unsigned char mipmapRes16[16][16][3]; // Storage for mipmap.
static unsigned char mipmapRes8[8][8][3]; // Storage for mipmap.
static unsigned char mipmapRes4[4][4][3]; // Storage for mipmap.
static unsigned char mipmapRes2[2][2][3]; // Storage for mipmap.
static unsigned char mipmapRes1[1][1][3]; // Storage for mipmap.

// Create mipmap images that are colored squares starting from 64 x 64 down to 1 x 1.
void createMipmaps(void)
{
   int i, j;

   // 创建Mipmap Level 0 的纹理图像
   for (i = 0; i < 64; i++) 
      for (j = 0; j < 64; j++) 
		 {
            mipmapRes64[i][j][0] = 0x00;
            mipmapRes64[i][j][1] = 0x00;
            mipmapRes64[i][j][2] = 0xFF;
		 }
   //////////////// TO DO ////////////////
   // 创建Mipmap Level 1 对应的纹理图像

   // 创建Mipmap Level 2 对应的纹理图像
   
   // 创建Mipmap Level 3 对应的纹理图像
   
   // 创建Mipmap Level 4 对应的纹理图像
   
   // 创建Mipmap Level 5 对应的纹理图像

   // 创建Mipmap Level 6 对应的纹理图像

   ////////////////////////////////////////
  
}

// Routine to load mipmaps. 
void loadMipmaps()			
{
   // Create texture object texture[0]. 
   glBindTexture(GL_TEXTURE_2D, texture[0]); 

   // Set texture parameters.
   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_NEAREST);
   glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

   ///////////////////////////////// TO DO /////////////////////////////////////////////////
   //////////////////通过glTexImage2D设置各个Mipmap等级的纹理图像/////////////////////////////////

   //设置Mipmap等级0的纹理图像

   //设置Mipmap等级1的纹理图像

   //设置Mipmap等级2的纹理图像
   
   //设置Mipmap等级3的纹理图像
   
   //设置Mipmap等级4的纹理图像
   
   //设置Mipmap等级5的纹理图像
   
   //设置Mipmap等级6的纹理图像

   /////////////////////////////////////////////////////////////////////////////////////////
}

// Initialization routine.
void setup(void)
{    
   glClearColor(1.0, 1.0, 1.0, 0.0); 

   // Create texture index array.
   glGenTextures(1, texture); 

   // Create and load mipmaps.
   createMipmaps();
   loadMipmaps();

  // Specify how texture values combine with current surface color values.
   glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE); 

   // Turn on OpenGL texturing.
   glEnable(GL_TEXTURE_2D); 
}

// Drawing routine.
void drawScene(void)
{
   glClear(GL_COLOR_BUFFER_BIT);

   glLoadIdentity();
   gluLookAt(0.0, 0.0, 6.0 + d, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0);

   // Map texture onto a square polygon.
   glBindTexture(GL_TEXTURE_2D, texture[0]); 
   glBegin(GL_POLYGON);
      glTexCoord2f(0.0, 0.0); glVertex3f(-5.0, -5.0, 0.0);
      glTexCoord2f(1.0, 0.0); glVertex3f(5.0, -5.0, 0.0);
      glTexCoord2f(1.0, 1.0); glVertex3f(5.0, 5.0, 0.0);
      glTexCoord2f(0.0, 1.0); glVertex3f(-5.0, 5.0, 0.0);
   glEnd();

   glutSwapBuffers();	
}

// OpenGL window reshape routine.
void resize(int w, int h)
{
   glViewport(0, 0, w, h);
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();
   glFrustum(-5.0, 5.0, -5.0, 5.0, 5.0, 2000.0);
   glMatrixMode(GL_MODELVIEW);
   glLoadIdentity();
}

// Keyboard input processing routine.
void keyInput(unsigned char key, int x, int y)
{
   switch(key) 
   {
      case 27:
         exit(0);
         break;
      default:
         break;
   }
}

// Callback routine for non-ASCII key entry.
void specialKeyInput(int key, int x, int y)
{
   if (key == GLUT_KEY_DOWN) 
   {
      if (d > 0.0) d -= 0.2;
   }
   if (key == GLUT_KEY_UP) 
   {
      d += 0.2;
   }
   glutPostRedisplay();
}

// Routine to output interaction instructions to the C++ window.
void printInteraction(void)
{
   cout << "Interaction:" << endl;
   cout << "Press the up and down arrow keys to move the square." << endl;
}

// Main routine.
int main(int argc, char **argv) 
{
   printInteraction();
   glutInit(&argc, argv);

   glutInitContextVersion(2, 1);
   glutInitContextProfile(GLUT_COMPATIBILITY_PROFILE);

   glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);
   glutInitWindowSize(500, 500);
   glutInitWindowPosition(5, 5);
   glutCreateWindow("mipmapLevels");
   glutDisplayFunc(drawScene);
   glutReshapeFunc(resize);
   glutKeyboardFunc(keyInput);
   glutSpecialFunc(specialKeyInput);

   glewExperimental = GL_TRUE;
   glewInit();

   setup(); 

   glutMainLoop();
}
