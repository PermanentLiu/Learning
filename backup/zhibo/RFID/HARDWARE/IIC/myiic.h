#ifndef __MYIIC_H
#define __MYIIC_H
#include "sys.h"
//////////////////////////////////////////////////////////////////////////////////	 
//������ֻ��ѧϰʹ�ã�δ���������ɣ��������������κ���;
//ALIENTEKս��STM32������
//IIC���� ����	   
//����ԭ��@ALIENTEK
//������̳:www.openedv.com
//�޸�����:2012/9/9
//�汾��V1.0
//��Ȩ���У�����ؾ���
//Copyright(C) �������������ӿƼ����޹�˾ 2009-2019
//All rights reserved									  
//////////////////////////////////////////////////////////////////////////////////

////IO��������
//#define SDA_IN()  {GPIOB->CRH&=0X0FFFFFFF;GPIOB->CRH|=8<<28;}
//#define SDA_OUT() {GPIOB->CRH&=0X0FFFFFFF;GPIOB->CRH|=3<<28;}

////IO��������	 
//#define IIC_SCL    PBout(13) //SCL
//#define IIC_SDA    PBout(15) //SDA	 
//#define READ_SDA   PBin(15)  //����SDA 

//IO��������
//#define SDA_IN()  {GPIOB->CRL&=0XFF0FFFFF;GPIOB->CRL|=8<<20;}
//#define SDA_OUT() {GPIOB->CRL&=0XFF0FFFFF;GPIOB->CRL|=3<<20;}

////IO��������	 
//#define IIC_SCL    PBout(3) //SCL
//#define IIC_SDA    PBout(5) //SDA	 
//#define READ_SDA   PBin(5)  //����SDA 

#define SDA_IN()  {GPIOC->CRL&=0XFFFFFF0F;GPIOC->CRL|=8<<4;}
#define SDA_OUT() {GPIOC->CRL&=0XFFFFFF0F;GPIOC->CRL|=3<<4;}
//IO��������	 
#define IIC_SCL    PCout(0) //SCL
#define IIC_SDA    PCout(1) //SDA	 
#define READ_SDA   PCin(1)  //����SDA 

//IIC���в�������
void IIC_Init(void);                //��ʼ��IIC��IO��				 
void IIC_Start(void);				//����IIC��ʼ�ź�
void IIC_Stop(void);	  			//����IICֹͣ�ź�
void IIC_Send_Byte(u8 txd);			//IIC����һ���ֽ�
u8 IIC_Read_Byte(unsigned char ack);//IIC��ȡһ���ֽ�
u8 IIC_Wait_Ack(void); 				//IIC�ȴ�ACK�ź�
void IIC_Ack(void);					//IIC����ACK�ź�
void IIC_NAck(void);				//IIC������ACK�ź�

void IIC_Write_One_Byte(u8 daddr,u8 addr,u8 data);
u8 IIC_Read_One_Byte(u8 daddr,u8 addr);	  
#endif















