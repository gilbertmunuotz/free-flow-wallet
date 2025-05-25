import type { Metadata } from "next";
import "./globals.css";
import { ThemeProvider } from "@/components/theme-provider"
import { SessionProvider } from "next-auth/react";
import { Slide, ToastContainer } from 'react-toastify';

export const metadata: Metadata = {
  title: "Free Flow Wallet",
};

export default function RootLayout({ children, }: Readonly<{ children: React.ReactNode; }>) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body>
        <SessionProvider>
          <ThemeProvider
            attribute="class"
            defaultTheme="system"
            enableSystem
            disableTransitionOnChange
          >
            {children}
            <ToastContainer
              position="top-center"
              transition={Slide}
              autoClose={5000}
              theme="colored"
              pauseOnHover
            />
          </ThemeProvider>
        </SessionProvider>
      </body>
    </html>
  );
}