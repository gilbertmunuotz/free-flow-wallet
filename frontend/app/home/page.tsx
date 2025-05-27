import { ModeToggle } from "@/components/theme";
import { Card, CardContent, CardHeader, CardTitle, } from "@/components/ui/card"
import getBalance from "./_action";


export default async function Page() {

    const balanceData = await getBalance()

    return (
        <div className="flex flex-col min-h-screen bg-white dark:bg-black">
            {/* Topbar inside content area (excluding sidebar) */}
            <div className="flex justify-end mr-3 pb-2 border-b border-gray-200 dark:border-gray-800">
                <ModeToggle />
            </div>

            {/* Main grid content, placed below toggle */}
            <main className="flex-1 grid grid-cols-1 md:grid-cols-[35%_65%] min-h-[300px]">
                <div className="justify-center p-6">
                    <Card className="bg-white dark:bg-sky-600">
                        <CardHeader>
                            <CardTitle className="text-center text-lg">Wallet Amount 💰</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p className="text-xl">💲{balanceData?.balance ?? 0} Cash</p>
                        </CardContent>
                    </Card>
                </div>
                <div className="items-center justify-center p-6">
                    <Card className="bg-white dark:bg-sky-600">
                        <CardHeader>
                            <CardTitle className="text-center text-lg">Spending Analytics 📈</CardTitle>
                        </CardHeader>
                        <CardContent>
                            <p>Spending Analytics</p>
                        </CardContent>
                    </Card>
                </div>
            </main>

        </div>
    );
}