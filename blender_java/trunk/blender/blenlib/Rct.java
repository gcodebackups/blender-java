/*
 * 
 * rct.c
 * 
 * april 95
 * 
 * $Id: rct.c 20379 2009-05-24 13:29:29Z ton $
 *
 * A minimalist lib for functions doing stuff with rectangle structs.
 *
 * ***** BEGIN GPL LICENSE BLOCK *****
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * The Original Code is Copyright (C) 2001-2002 by NaN Holding BV.
 * All rights reserved.
 *
 * The Original Code is: all of this file.
 *
 * Contributor(s): none yet.
 *
 * ***** END GPL LICENSE BLOCK *****
 *
 */
package blender.blenlib;

import blender.makesdna.sdna.rctf;
import blender.makesdna.sdna.rcti;

public class Rct {

    public static boolean BLI_rcti_is_empty(rcti rect) {
        return ((rect.xmax <= rect.xmin) ||
                (rect.ymax <= rect.ymin));
    }

    public static boolean BLI_in_rcti(rcti rect, int x, int y) {
        if (x < rect.xmin) {
            return false;
        }
        if (x > rect.xmax) {
            return false;
        }
        if (y < rect.ymin) {
            return false;
        }
        if (y > rect.ymax) {
            return false;
        }
        return true;
    }

    public static boolean BLI_in_rctf(rctf rect, float x, float y) {
        if (x < rect.xmin) {
            return false;
        }
        if (x > rect.xmax) {
            return false;
        }
        if (y < rect.ymin) {
            return false;
        }
        if (y > rect.ymax) {
            return false;
        }
        return true;
    }

//void BLI_union_rctf(rctf *rct1, rctf *rct2)
//{
//
//	if(rct1.xmin>rct2.xmin) rct1.xmin= rct2.xmin;
//	if(rct1.xmax<rct2.xmax) rct1.xmax= rct2.xmax;
//	if(rct1.ymin>rct2.ymin) rct1.ymin= rct2.ymin;
//	if(rct1.ymax<rct2.ymax) rct1.ymax= rct2.ymax;
//}
//
//void BLI_union_rcti(rcti *rct1, rcti *rct2)
//{
//
//	if(rct1.xmin>rct2.xmin) rct1.xmin= rct2.xmin;
//	if(rct1.xmax<rct2.xmax) rct1.xmax= rct2.xmax;
//	if(rct1.ymin>rct2.ymin) rct1.ymin= rct2.ymin;
//	if(rct1.ymax<rct2.ymax) rct1.ymax= rct2.ymax;
//}

public static void BLI_init_rctf(rctf rect, float xmin, float xmax, float ymin, float ymax)
{
	if(xmin <= xmax) {
		rect.xmin= xmin;
		rect.xmax= xmax;
	}
	else {
		rect.xmax= xmin;
		rect.xmin= xmax;
	}
	if(ymin <= ymax) {
		rect.ymin= ymin;
		rect.ymax= ymax;
	}
	else {
		rect.ymax= ymin;
		rect.ymin= ymax;
	}
}

public static void BLI_init_rcti(rcti rect, int xmin, int xmax, int ymin, int ymax)
{
	if(xmin <= xmax) {
		rect.xmin= xmin;
		rect.xmax= xmax;
	}
	else {
		rect.xmax= xmin;
		rect.xmin= xmax;
	}
	if(ymin <= ymax) {
		rect.ymin= ymin;
		rect.ymax= ymax;
	}
	else {
		rect.ymax= ymin;
		rect.ymin= ymax;
	}
}

public static void BLI_translate_rcti(rcti rect, int x, int y)
{
	rect.xmin += x;
	rect.ymin += y;
	rect.xmax += x;
	rect.ymax += y;
}
//void BLI_translate_rctf(rctf *rect, float x, float y)
//{
//	rect.xmin += x;
//	rect.ymin += y;
//	rect.xmax += x;
//	rect.ymax += y;
//}

    public static boolean BLI_isect_rctf(rctf src1, rctf src2, rctf dest) {
        float xmin, xmax;
        float ymin, ymax;

        xmin = (src1.xmin) > (src2.xmin) ? (src1.xmin) : (src2.xmin);
        xmax = (src1.xmax) < (src2.xmax) ? (src1.xmax) : (src2.xmax);
        ymin = (src1.ymin) > (src2.ymin) ? (src1.ymin) : (src2.ymin);
        ymax = (src1.ymax) < (src2.ymax) ? (src1.ymax) : (src2.ymax);

        if (xmax >= xmin && ymax >= ymin) {
            if (dest != null) {
                dest.xmin = xmin;
                dest.xmax = xmax;
                dest.ymin = ymin;
                dest.ymax = ymax;
            }
            return true;
        } else {
            if (dest != null) {
                dest.xmin = 0;
                dest.xmax = 0;
                dest.ymin = 0;
                dest.ymax = 0;
            }
            return false;
        }
    }

public static boolean BLI_isect_rcti(rcti src1, rcti src2, rcti dest)
{
	int xmin, xmax;
	int ymin, ymax;

	xmin = (src1.xmin) > (src2.xmin) ? (src1.xmin) : (src2.xmin);
	xmax = (src1.xmax) < (src2.xmax) ? (src1.xmax) : (src2.xmax);
	ymin = (src1.ymin) > (src2.ymin) ? (src1.ymin) : (src2.ymin);
	ymax = (src1.ymax) < (src2.ymax) ? (src1.ymax) : (src2.ymax);

	if(xmax>=xmin && ymax>=ymin) {
		if(dest!=null) {
			dest.xmin = xmin;
			dest.xmax = xmax;
			dest.ymin = ymin;
			dest.ymax = ymax;
		}
		return true;
	}
	else {
		if(dest!=null) {
			dest.xmin = 0;
			dest.xmax = 0;
			dest.ymin = 0;
			dest.ymax = 0;
		}
		return false;
	}
}

public static void clear(rcti rect) {
	rect.xmin=0;
	rect.xmax=0;
	rect.ymin=0;
	rect.ymax=0;
}

}