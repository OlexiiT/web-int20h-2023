// middleware.ts
import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

// This function can be marked `async` if using `await` inside
export function middleware(request: NextRequest) {
  const url = request.nextUrl.clone()   
  if (url.pathname === '/') {
    url.pathname = '/recipes'
    return NextResponse.rewrite(url)   
  } 
  return NextResponse.next()
}
